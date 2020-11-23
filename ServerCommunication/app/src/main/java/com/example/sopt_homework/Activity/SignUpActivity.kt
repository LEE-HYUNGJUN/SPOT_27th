package com.example.sopt_homework.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sopt_homework.R
import com.example.sopt_homework.RequestData.RequestSignUpData
import com.example.sopt_homework.ResponseData.ResponseSignUpData
import com.example.sopt_homework.api.SignUpServiceimpl
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val sharedEdit= sharedPref.edit()

        ok_btn.setOnClickListener {
            if(edit_name.text.toString() == "" || edit_id.text.toString() == "" || edit_password.text.toString() == ""){
                Toast.makeText(this, "빈칸이 존재합니다! 전부 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
            else {

                var intent = Intent(this, MainActivity::class.java)
                intent.putExtra("ID", edit_id.text.toString())
                intent.putExtra("PW", edit_password.text.toString())
                intent.putExtra("NAME",edit_name.text.toString())

                sharedEdit.putString("pname",edit_name.text.toString())
                sharedEdit.apply()
                setResult(Activity.RESULT_OK,intent)

                val email = edit_id.text.toString()
                val password = edit_password.text.toString()
                val userName = edit_name.text.toString()

                val call : Call<ResponseSignUpData> = SignUpServiceimpl.service.postSignUp(
                    RequestSignUpData(email = email , password = password,userName = userName)
                )
                call.enqueue(object : Callback<ResponseSignUpData>{
                    override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                        Log.d("SignUpError",t.message)
                    }

                    override fun onResponse(
                        call: Call<ResponseSignUpData>,
                        response: Response<ResponseSignUpData>
                    ) {
                        response.takeIf { it.isSuccessful }
                            ?.body()
                            ?.let {
                                it.data.let {
                                    data ->
                                    Toast.makeText(this@SignUpActivity, "회원가입완료!", Toast.LENGTH_LONG).show()
                                    finish()
                                }
                            }
                    }
                })
            }
        }

    }
}
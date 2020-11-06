package com.example.sopt_homework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

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
                Toast.makeText(this, "회원가입완료!", Toast.LENGTH_LONG).show()
                var intent = Intent(this,MainActivity::class.java)
                intent.putExtra("ID", edit_id.text.toString())
                intent.putExtra("PW", edit_password.text.toString())
                intent.putExtra("NAME",edit_name.text.toString())

                sharedEdit.putString("pname",edit_name.text.toString())
                sharedEdit.apply()
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }

    }
}
package com.example.sopt_homework.Activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sopt_homework.ProfileViewPager
import com.example.sopt_homework.R
import com.example.sopt_homework.RequestLoginData
import com.example.sopt_homework.ResponseLoginData
import com.example.sopt_homework.api.SampleServiceimpl
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val sharedEdit= sharedPref.edit()

        id_edit.setText(sharedPref.getString("id",""))
        password_edit.setText(sharedPref.getString("pw",""))

        /*if(sharedPref.getString("id","") != "" && sharedPref.getString("pw","") != ""){
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }*/

        login_btn.setOnClickListener {
            if (id_edit.text.toString() == "" || password_edit.text.toString() == "") {
                Toast.makeText(this, "빈칸을 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
            else {
                //Toast.makeText(this, "로그인 됐습니다!", Toast.LENGTH_SHORT).show()
                /*sharedEdit.putString("id",id_edit.text.toString())
                sharedEdit.putString("pw",password_edit.text.toString())
                sharedEdit.apply()*/
                val email : String = id_edit.text.toString()
                val password:String = password_edit.text.toString()

                val call : Call<ResponseLoginData> = SampleServiceimpl.service.postLogin(
                    RequestLoginData(email = email, password = password)
                )
                call.enqueue(object : Callback<ResponseLoginData>{
                    override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {

                    }
                    override fun onResponse(
                        call: Call<ResponseLoginData>,
                        response: Response<ResponseLoginData>
                    ) {
                        response.takeIf { it.isSuccessful }
                            ?.body()
                            ?.let {
                                    it.data.let { data ->
                                        Toast.makeText(this@MainActivity,"${data.userName}님 환영합니다"
                                        ,Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this@MainActivity, ProfileViewPager::class.java)
                                        // intent.putExtra("name",sharedPref.getString("pname",""))
                                        startActivity(intent)
                                    }



                            } ?: showError(response.errorBody())
                    }

                    private fun showError(error: ResponseBody?) {
                            val e = error ?: return
                            val ob = JSONObject(e.string())
                            Toast.makeText(this@MainActivity,ob.getString("message"),Toast.LENGTH_SHORT).show()
                    }
                })



               /* val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)*/
               /* val intent = Intent(this,RecyclerView::class.java)
                startActivity(intent)*/
                /*val intent = Intent(this, ProfileViewPager::class.java)
                intent.putExtra("name",sharedPref.getString("pname",""))
                startActivity(intent)*/

            }
        }
        signup_btn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent,0)
            
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


       if (requestCode == 0){
           if(resultCode == Activity.RESULT_OK){
               var id : String? = data?.getStringExtra("ID")
               var pw : String? = data?.getStringExtra("PW")
               var name :String? = data?.getStringExtra("NAME")
               id_edit.setText(id)
               password_edit.setText(pw)
           }
       }



    }

}
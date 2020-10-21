package com.example.sopt_homework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val sharedEdit= sharedPref.edit()

        id_edit.setText(sharedPref.getString("id",""))
        password_edit.setText(sharedPref.getString("pw",""))

        if(sharedPref.getString("id","") != "" && sharedPref.getString("pw","") != ""){
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }

        login_btn.setOnClickListener {
            if (id_edit.text.toString() == "" || password_edit.text.toString() == "") {
                Toast.makeText(this, "빈칸을 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "로그인 됐습니다!", Toast.LENGTH_SHORT).show()
                sharedEdit.putString("id",id_edit.text.toString())
                sharedEdit.putString("pw",password_edit.text.toString())
                sharedEdit.apply()

                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
            }
        }
        signup_btn.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivityForResult(intent,0)
            
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


       if (requestCode == 0){
           if(resultCode == Activity.RESULT_OK){
               var id : String? = data?.getStringExtra("ID")
               var pw : String? = data?.getStringExtra("PW")
               id_edit.setText(id)
               password_edit.setText(pw)
           }
       }
    }

}
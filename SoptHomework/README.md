### 안드로이드 세미나 1주차 과제
-------------------------------
#### 1. MainActivity
##### 회원가입 버튼 클릭

<pre>
 signup_btn.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivityForResult(intent,0)
            
        }
</pre>
인텐트 객체를 생성해서 SignUpActivity로 보낸다.

startActivityForResult를 이용해 Intent를 requestCode = 0 과 함께 보낸다.

회원가입버튼을 클릭하면 회원가입 화면이 호출된다.

##### onActivityResult 함수

<pre>
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
</pre>


#### 2. SignUpActivity
##### 로그인 버튼 클릭
- EditText에 빈칸이 존재할경우 
<pre>
ok_btn.setOnClickListener {
            if(edit_name.text.toString() == "" || edit_id.text.toString() == "" || edit_password.text.toString() == ""){
                Toast.makeText(this, "빈칸이 존재합니다! 전부 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
</pre>
토스트 메시지를 띄움.

- EditText에 빈칸이 없는 경우 
<pre>
 Toast.makeText(this, "회원가입완료!", Toast.LENGTH_LONG).show()
                var intent = Intent(this,MainActivity::class.java)
                intent.putExtra("ID", edit_id.text.toString())
                intent.putExtra("PW", edit_password.text.toString())
                setResult(Activity.RESULT_OK,intent)
                finish()
</pre>
인텐트 객체를 생성한다. 생성된 Intent에 ID값과 Password값을 넣어준다.

MainActivity에서 startActivityForResult()로 전송되었기에 setResult(Activity.RESULT_OK,intent)를 해준다. 

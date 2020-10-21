### 안드로이드 세미나 1주차 과제
-------------------------------
### 1. MainActivity
#### 회원가입 버튼 클릭

<pre>
 signup_btn.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivityForResult(intent,0)
            
        }
</pre>
인텐트 객체를 생성해서 SignUpActivity로 보낸다.

startActivityForResult를 이용해 Intent를 requestCode = 0 과 함께 보낸다.

회원가입버튼을 클릭하면 회원가입 화면이 호출된다.

#### onActivityResult 함수

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

startActivityForResult를 이용해 회원가입창으로 Intent를 보내 SignUpActivtiy에서 다시 MainActivity에 Intent를 받기 위해서는 

onActivityResult 함수가 필요하다

MainActivity에서 requestCode = 0으로 보냈고, SignUpActivity에서 Activity.RESULT_OK라는 메시지가 resultCode에 설정돼서 온다면

Main 로그인 화면 창에 회원가입할때 적힌 id와 password를 화면에 띄어준다.


#### SharedPreferences 객체 설정

<pre>
 val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val sharedEdit= sharedPref.edit()
</pre>

로그인시에 id와 pw를 저장하기 위해 SharedPreferences를 이용한다.

먼저, SharedPreferences 객체를 생성한다. 객체 이름을 "pref"로 설정하고, 뒤에 모드를 정해준다. 대부분 Context.MODE_PRIVATE를 사용한다.

그 다음 Edit 객체를 만들어준다. 정보를 SharedPreferences에 입력하는 것은 모두 Edit으로 진행된다. 

#### SharedPreferences에 값 넣어주기
<pre>
 sharedEdit.putString("id",id_edit.text.toString())
                sharedEdit.putString("pw",password_edit.text.toString())
                sharedEdit.apply()
</pre>

putString을 이용해 정보를 넣어준다. 넣어주는 값의 이름을 "id" 또는 "pw"로 설정하고, id_edit.text.toString()을 SharedPreferences 에 넣어준다.

id_edit.text.toString() 는 id_edit라는 이름을 가진 EditText에서 값을 가져오는 것이다.

putString을 해서 끝나는 것이 아니라 sharedEdit.apply()를 실행해야 모든 값이 넣어진다.


#### SharedPreferences에서 값 가져오기
<pre>
if(sharedPref.getString("id","") != "" && sharedPref.getString("pw","") != ""){
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
</pre>

SharedPreferences에 넣은 값이 문자열이기 때문에 getString을 이용해 값을 가져온다. 

여기서 중요한것은 Edit객체를 이용하는 것이 아니다. 

값을 넣어주는 것은 Edit을 이용하지만, 값을 가져올때는 SharePreferences 객체를 이용한다.

위의 코드는 등록한 ID와 Password값이 존재하다면  HomeActivity로 보내는 작업을 한다.

### 2. SignUpActivity
#### 로그인 버튼 클릭
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

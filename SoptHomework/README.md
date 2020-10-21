### 안드로이드 세미나 1주차 과제
-------------------------------
##### 1. SignUpActivity
- 로그인 버튼 클릭

<pre>
ok_btn.setOnClickListener {
            if(edit_name.text.toString() == "" || edit_id.text.toString() == "" || edit_password.text.toString() == ""){
                Toast.makeText(this, "빈칸이 존재합니다! 전부 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
</pre>
  

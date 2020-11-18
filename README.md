## 안드로이드 세미나 1주차 과제

**1.MainActivity**

**2.SignUpActivity**

**3.activity_sign_up.xml**

### 1. MainActivity

- #### 회원가입 버튼 클릭

```kotlin
signup_btn.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivityForResult(intent,0)
     }
```

인텐트 객체를 생성해서 SignUpActivity로 보낸다.

**startActivityForResult**를 이용해 Intent를 requestCode = 0 과 함께 보낸다.

회원가입버튼을 클릭하면 회원가입 화면이 호출된다.  



- #### onActivityResult 함수

```kotlin
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
```



**startActivityForResult**에 대한 처리 즉, MainActivity에서 **startActivityForResult**를 통해 SecondActivity에서의 특정 행위에 대한 결과값을 처리하기 위해 **onActivityResult**를 *오버라이드*합니다.

**startActivityForResult**를 이용해 회원가입창으로 Intent를 보내 SignUpActivtiy에서 다시 MainActivity에 Intent를 받기 위해서는 **onActivityResult** 함수가 필요하다

MainActivity에서 *requestCode = 0*으로 보냈고, SignUpActivity에서 **Activity.RESULT_OK**라는 메시지가 resultCode에 설정돼서 온다면

Main 로그인 화면 창에 회원가입할때 적힌 id와 password를 화면에 띄어준다.    

**onActivityResult**에서 정해준 requestCode와 resultCode를 이용해 각각의 다른 처리를 해주는 분기처리를 할 수 있기에 **startActivity** 보다 다양한 기능을 가지고 있다. 



- #### SharedPreferences 객체 설정

```kotlin 
val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
val sharedEdit= sharedPref.edit()
```


로그인시에 id와 pw를 저장하기 위해 **SharedPreferences**를 이용한다.

먼저, **SharedPreferences** 객체를 생성한다. 객체 이름을 "pref"로 설정하고, 뒤에 모드를 정해준다. 대부분 Context.MODE_PRIVATE를 사용한다.

그 다음 **Edit 객체**를 만들어준다. 정보를 SharedPreferences에 입력하는 것은 모두 Edit으로 진행된다.     



- #### SharedPreferences에 값 넣어주기

```kotlin
 sharedEdit.putString("id",id_edit.text.toString())
 sharedEdit.putString("pw",password_edit.text.toString())
 sharedEdit.apply()
```

*putString*을 이용해 정보를 넣어준다. 넣어주는 값의 이름을 "id" 또는 "pw"로 설정하고, id_edit.text.toString()을 SharedPreferences 에 넣어준다.

id_edit.text.toString() 는 id_edit라는 이름을 가진 EditText에서 값을 가져오는 것이다.

putString을 해서 끝나는 것이 아니라 sharedEdit.apply()를 실행해야 모든 값이 넣어진다.    



- #### SharedPreferences에서 값 가져오기

```kotlin
if(sharedPref.getString("id","") != "" && sharedPref.getString("pw","") != ""){
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
</pre>
```

**SharedPreferences**에 넣은 값이 문자열이기 때문에 getString을 이용해 값을 가져온다. 

여기서 중요한것은 <u>Edit객체</u>를 이용하는 것이 아니다. 

값을 넣어주는 것은 <u>Edit을 이용</u>하지만, 값을 가져올때는 <u>SharePreferences 객체</u>를 이용한다.

위의 코드는 등록한 ID와 Password값이 존재하다면  HomeActivity로 보내는 작업을 한다.


--------------------------------------


### 2. SignUpActivity

- #### 로그인 버튼 클릭

  - ​	EditText에 빈칸이 존재할경우 

    ```kotlin
    ok_btn.setOnClickListener {
                if(edit_name.text.toString() == "" || edit_id.text.toString() == "" || edit_password.text.toString() == ""){
                    Toast.makeText(this, "빈칸이 존재합니다! 전부 입력해주세요!", Toast.LENGTH_SHORT).show()
                }
    ```

    빈칸이 존재한다는 토스트 메시지를 띄운다

    

  - ​    EditText에 빈칸이 없는 경우 

```kotlin
 Toast.makeText(this, "회원가입완료!", Toast.LENGTH_LONG).show()
                var intent = Intent(this,MainActivity::class.java)
                intent.putExtra("ID", edit_id.text.toString())
                intent.putExtra("PW", edit_password.text.toString())
                setResult(Activity.RESULT_OK,intent)
                finish()

```

인텐트 객체를 생성한다. 생성된 Intent에 ID값과 Password값을 넣어준다.

MainActivity에서 **startActivityForResult()** 로 전송되었기에 **setResult(Activity.RESULT_OK,intent)** 를 해준다. 

<hr>


### 3. activity_sign_up.xml

<pre>
    android:hint="비밀번호를 입력하시오"
    android:inputType="numberPassword"
</pre>


EditText에 hint와 inputType 속성을 넣어준다. 

------
### Seminar 02. RecyclerView

- ##### ProfileData

  ```kotlin
  @Parcelize
  data class ProfileData (
      var title : String,
      var subTitle : String,
      var date_title : String,
      var date_subTitle : String,
      var contents : String
  ) : Parcelable
  ```

  RecyclerView에서 이용될 Data class를 먼저 생성해준다. 

  여기서 **@Parcelize**를 사용했는데 Parcelize란  **A Activity에서 B Activity로 데이터를 한꺼번에 전달하여 받아 볼 수 있도록 해 주는 것**이다. **Parcelize**를 이용하면 intent로 data를 넣고 꺼낼때 하나 하나 입력할 필요가 없다. <br><br>

- ##### ViewHolder

  ```kotlin
  class ProfileViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
  
      private  val title : TextView = itemView.findViewById(R.id.title_txt)
      private val subtitle : TextView = itemView.findViewById(R.id.subtitle_txt)
  
      fun onBind(data : ProfileData){
          title.text = data.title
          subtitle.text = data.subTitle
      }
  }
  ```

  **ViewHolder**는 **RecyclerView.ViewHolder** 를 상속받아 구현하게 되며, ViewHolder를 통해 item을 표시할 View를 보관하고 필요에 따라 <u>재사용</u> 할 수 있게 된다. 대용량의 데이터라도 소수의 View를 이용하여 표현 가능 하다.

  **onBind** 함수를 이용해서 View에 data를 bind 시킨다.

  RecyclerView 는 화면에 표시되는 개수만큼의 **ViewHolder**와 여분의 **ViewHolder**를 기본으로 생성하여 스크롤에 대비한다. <br><br>

- ##### Adapter

  ```kotlin
  class ProfileAdapter (private val context : Context) : RecyclerView.Adapter<ProfileViewHolder>(){
  
      var data = mutableListOf<ProfileData>()
      var layoutitem = R.layout.item_list
  
      override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
          val view = LayoutInflater.from(context).inflate(layoutitem,parent,false)
          return ProfileViewHolder(view)
      }
  
      override fun getItemCount(): Int = data.size
  
      override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
          holder.onBind(data[position])
  
          holder.itemView.setOnClickListener {
              mitemClickListener.onClick(it,position)
          }
      }
  ```

  **Adapter**는 ViewHolder를 <u>생성</u>하고, data를 ViewHolder에 <u>넣어주는</u> 역할을 한다. 

  **Adapter**는 3가지 함수를 override 해야 한다.

   1. *onCreateViewHolder* : 앞에서 정의한 뷰 홀더가 새롭게 만들어지는 시점에 호출이 된다.

      그 안에서 각각의 아이템을 위해 정의한 XML 레이아웃을 뷰 객체로 만들어준다.

   2. *getItemCount* : RecyclerView Adapter 어댑터에서 관리하는 아이템의 개수를 반환합니다.

   3. *onBindViewHolder* : 매서드를 통해 생성한 객체를 재활용하여 내부의 데이터만 바꾸는 형식이다. Adapter는 ViewHolder를 position(위치) 기반으로 할당하고, onBindViewHolder 콜백 호출을 받았을때, 위치(position) 기반의 데이터를 할당된 ViewHolder에 표시할 수 있다.

  

- ##### RecyclerViewActivity

  ```kotlin
  profileAdapter = ProfileAdapter(this)
          //main_rcv.layoutManager = GridLayoutManager(this,columnsCount)
          main_rcv.layoutManager = LinearLayoutManager(this)
          main_rcv.adapter = profileAdapter
  
  
          profileAdapter.data = mutableListOf(ProfileData(
                  "이름", "이형준", "작성날짜", "10월 22일", "안녕하십니까! 저는 YB 이형준입니다!"))
  		profileAdapter.notifyDataSetChanged()
  ```

  Activity에서  **Adpater 객체를 생성**하여, 객체를 이용해 데이터를 넣어준다. 넣은 데이터는 ViewHolder에 뿌려지게 된다. 

------

- ##### Click 이벤트 처리 

  click 이벤트를 Adapter에서 처리 하지 않고, 외부 Activity나 Fragment에서 처리하는 방법을 이용하였다. 

  - Adapter 내 커스텀 리스너 interface 정의

    ```kotlin
     interface ItemClickListener{
            fun onClick(view : View, position: Int)
        }
    ```

  - 리스너 객체 참조를 저장하는 변수와  Adapter에 전달하는 메서드 생성

    ```kotlin
    // 리스너 객체 참조를 저장하는 변수
    	private lateinit var mitemClickListener: ItemClickListener
    
        // ItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
        fun setItemClickListener(itemClickListener: ItemClickListener){
            this.mitemClickListener = itemClickListener
        }
    ```

  - Adpater내 onBindViewHolder 함수 내 onClickListener 설정

    ```kotlin
    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
            holder.onBind(data[position])
    
            holder.itemView.setOnClickListener {
                mitemClickListener.onClick(it,position)
            }
        }
    ```

  - Activity에서 사용

    ```kotlin
     profileAdapter.setItemClickListener(object : ProfileAdapter.ItemClickListener {
                override fun onClick(view: View, position: Int) {
                 	Log.d("SSS","${position}번 리스트 선택")
                    val intent = Intent(baseContext, ResultActivity::class.java)
                    intent.putExtra("data", profileAdapter.data[position])
                    startActivity(intent)
                }
    
            })
    ```

    


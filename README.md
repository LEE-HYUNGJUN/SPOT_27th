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

  - 	EditText에 빈칸이 존재할경우 

    ```kotlin
    ok_btn.setOnClickListener {
                if(edit_name.text.toString() == "" || edit_id.text.toString() == "" || edit_password.text.toString() == ""){
                    Toast.makeText(this, "빈칸이 존재합니다! 전부 입력해주세요!", Toast.LENGTH_SHORT).show()
                }
    ```

    빈칸이 존재한다는 토스트 메시지를 띄운다

    

  -     EditText에 빈칸이 없는 경우 

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

    

- Menu 버튼 생성

  - menu.xml 생성

    ```kotlin
    <menu xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:android="http://schemas.android.com/apk/res/android">
        <item android:title="linear" android:id="@+id/linear"/>
        <item android:title="grid" android:id="@+id/grid"/>
    </menu>
    ```

  - onCreateOptionsMenu 오버라이드 

    ```kotlin
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater = menuInflater
            inflater.inflate(R.menu.menu,menu)
    
            return super.onCreateOptionsMenu(menu)
        }
    ```

    **onCreateOptionsMenu**는 Activity가 호출될때 호출되는 함수로 생명 주기내에서 단 한 번만 호출된다. 해당 함수에서는 MenuInflater를 통하여 menu xml 파일을 객체로 생성해준다.

  - onOptionsItemSelected 오버라이드

    ```kotlin
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when(item.itemId){
                R.id.linear ->{
                    main_rcv.layoutManager = LinearLayoutManager(this)
                    profileAdapter.layoutitem = R.layout.item_list
                    main_rcv.adapter = profileAdapter
                    return true
                }
                R.id.grid ->{
                    main_rcv.layoutManager = GridLayoutManager(this,2)
                    profileAdapter.layoutitem = R.layout.list_grid
                    main_rcv.adapter = profileAdapter
                    return true
                }
                else ->{
                    return super.onOptionsItemSelected(item)
                }
            }
        }
    ```

    onOptionsItemSelected 함수에서는 특정 menu item을 클릭했을 때 호출되는 함수이다. when함수를 통해 item.itemId가 linear,grid 일 때로 나누어 레이아웃이 다르게 선택되도록 구현함.

------

## Seminar 03. Fragment

-----

3주차 과제의 큰 틀은 ViewPager2개, Fragement 5개로 구성된다. <br>
핵심은 ViewPager가 2개이므로 Adapter도 2개를 이용해야 하는 것이다.

*****

#### 1. Fragment

프래그먼트란 하나의 액티비티가 여러 개의 화면을 가지도록 만들기 위해 고안된 개념이다.

- 액티비티와 동일하게 레이아웃, 동작 처리, 생명주기를 가지고 있음
- 재사용성이 뛰어남
- 액티비티 내에서 실행 중 추가,제거,변경이 가능

중요한 것은 Activity는 안드로이드 시스템이 관리하지만, Fragment는 Activity가 관리한다.
```kotlin
supportFragmentManager.beginTransaction().add(R.id.fragment_container,fragment1).commit()
```
위의 코드에서 볼 수 있듯이 Fragment를 관리하기 위해 **FragmentManger**를 호출해야한다. 

여기서 **Fragment Transaction**의 역할이 중요하다

-**Fragment Transaction** 이란?

1. Fragment Manager의 역할 중 하나

2. 액티비티에서 프래그먼트를 추가,변경,삭제 작업을 수행

3. 수행한 트랜잭션의 상태를 백스택에 저장 가능

<u>supportFragmentManager</u>를 이용해 <u>beginTransaction()</u>을 호출하여 추가,제거, 변경등이 가능하다!<br><br>

- Fragment 생명주기 

  ![](C:\Users\LEEHJ\Desktop\fragment생명주기.PNG)

  프래그먼트에서는 뷰를 생성하는 시점이 정해져 있어 onCreateView() 에서 생성하고 뷰가 만들어지면 **onViewCreated()**가 콜백됩니다. Fragment에서 구현하고자 하는 것을 **onViewCreated()**에 적어준다. 

#### 2.ViewPager

ViewPager란 하나의 화면 안에서 여러가지 화면을 슬라이드 형식으로 보여줄 때 사용한다.

주로 하단 탭, 상단 탭과 연동하여 사용한다.

먼저, ViewPager에 필요한 Adapter를 작성해야 한다. 

- MainViewPagerAdapter
```kotlin
class MainViewPagerAdapter (fm: FragmentManager)
    : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    var fragments = listOf<Fragment>() //fragment를 담은 리스트

    override fun getItem(position: Int): Fragment = when(position) {
        0 -> ProfileFragment()
        1 -> PortFragment()
        2 -> SettingFragment()
        else -> throw IllegalStateException("오류입니다")
    }

    override fun getCount(): Int = fragments.size
}
```
Adpater는 **getItem()** ,**getCount()** 이 두가지를 무조건 override 해주어야 한다. 

또한, (fm: FragmentManager)를 보면 알듯이 FragmentManger를 필요로 한다.

**FragmentStatePagerAdapter**를 adapter가 상속한다. FragmentStatePagerAdapter 란 보여지는 화면 기준 양 옆 프래그먼트를 제외한 나머지를 완전히 파괴하고, 메모리 누수 관리에 효과적이다. 

getItem에서 전달 받은 position을 통해 어떤 Fragment를 호출할 것인지를 정해준다. 

- HomeViewPagerAdapter
```kotlin
class HomeViewPagerAdapter (fm: FragmentManager)
    : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    var fragments = listOf<Fragment>() //fragment를 담은 리스트

    override fun getItem(position: Int): Fragment = when(position){
        0 -> InfoFragment()
        1 -> OtherFragment()
        else -> throw IllegalStateException("오류입니다")
    }

    override fun getCount(): Int = fragments.size
}
```

Adapter를 구성했으면 ViewPager에 Adapter를 장착을 해주면 된다.
- ProfileViewPager
```kotlin
 private lateinit var mvp_Adapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_view_pager)

        mvp_Adapter = MainViewPagerAdapter(supportFragmentManager)
        mvp_Adapter.fragments = listOf(
            ProfileFragment(),
            PortFragment(),
            SettingFragment()
        )
        vp_main.adapter = mvp_Adapter
```
Adpater에 fragment를 리스트로 넣어준다.


그리고, xml에서 ViewPager인 vp_main을 불러와 adapter를 장착해준다.<br>



#### 3. BottomNavigation
1. ###### bottom_menu.xml 생성

```kotlin
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/menu_profile"
        android:icon="@drawable/ic_profile"
        android:title="Profile"/>
    <item
        android:id="@+id/menu_port"
        android:icon="@drawable/ic_port"
        android:title="Port"/>
    <item
        android:id="@+id/menu_setting"
        android:icon="@drawable/ic_setting"
        android:title="Setting"/>
</menu>
```
먼저 bottom_menu를 생성한다. 어떤 icon과 title을 가지고 있는지를 정해준다.

2. ###### bottom_navi_color 생성 (selector)

```kotlin
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="#86C3BA" android:state_checked="true"/>
    <item android:color="#9E9E9E" android:state_checked="false"/>
</selector>
```

selector를 생성해준다. <u>android:state_checked</u>를 이용해 체크가 될 경우와 안 될 경우의 색을 구분 짓는다.

3. ###### BottomNavigation 이벤트 처리

```kotlin
bottom_menu.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when(it.itemId){
                R.id.menu_profile -> index = 0
                R.id.menu_port -> index = 1
                R.id.menu_setting -> index = 2
            }
            vp_main.currentItem = index
            true
        }
```
네비게이션을 클릭했을 때 itemId 값을 통해 index 값을 정해준다. 

여기서 클릭해서 정해진 index가 FragmentAdapter의 position으로 들어가 각각의 Fragment를 호출한다.

#### 4. TabLayout
TabLayout은 상단 탭을 만들 때 주로 사용된다. BottomNavigation에 비해 위치 이동이 자유롭다.

```kotlin
 tl_profile.setupWithViewPager(vp_profile)
        tl_profile.apply{
            getTabAt(0)?.text = "Info"
            getTabAt(1)?.text = "Other"
        }
```
xml에서 정해준 tl_profile(=TabLayout)을 가져와 **setupWithViewPager**를 호출한다. 

setupWithViewPager를 이용해 vp_profile(=ViewPager)이라는 TabLayout를 이용하기 위한 ViewPager를 연결한다.

getTabAt(0)?.text = "Info" 를 이용해 각 Tab의 타이틀을 정해준다. 

------

# Seminar06. Server

##### 결과화면

<p float="left">
            <img width="400" height="350" src="https://user-images.githubusercontent.com/72328789/101057770-c1bfb180-35cf-11eb-8774-281a14dbe256.PNG">
            <img width="250" height="350" src="https://user-images.githubusercontent.com/72328789/101059079-3c3d0100-35d1-11eb-974a-6b041d47a47d.gif">
            <img width ="250" height="350" src="https://user-images.githubusercontent.com/72328789/101059283-6f7f9000-35d1-11eb-80ed-d49554a86484.gif">
</p>



##### 1. Retrofit interface 설계, 싱글톤 실제 구현체

```kotlin
interface SignUpService{
    @Headers("Content-Type:application/json")
    @POST("/users/signup")
    fun postSignUp(
        @Body body : RequestSignUpData
    ) : Call<ResponseSignUpData>
}
```

```kotlin
interface DummyService{
    @Headers("Content-Type:application/json")
    @POST("/api/users")
    fun postDummy(
        @Query("page") page : Int
    ) : Call<ResponseDummyData>
}
```

```kotlin
interface KakaoService{
    @Headers("Authorization:KakaoAK 310946a77e3b1f2d3f2ad957864a948f")
    @GET("/v2/search/web")
    fun getWebSearch(
        @Query("query") web : String
    ):Call<ResponseKakaoData>
}
```

- 식별 URL을 interface로 설계한다. URL에 들어가는 변경가능한 경로라면 @Path , 쿼리 매개변수를 사용할 경우 @Query로 표현한다.
- @POST 방식 외에도 @GET등이 있다.
- 요청하는 데이터가 있다면 @Body를 통해 RequestBody를 설정할 수 있다.

```kotlin
object SignUpServiceimpl{
    private const val BASE_URL = "http://15.164.83.210:3000"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : SignUpService = retrofit.create(SignUpService::class.java)
}
```

```kotlin
object DummyServiceimpl {
    private const val BASE_URL = "http://reqres.in"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : DummyService = retrofit.create(DummyService::class.java)
}
```

```kotlin
object KakaoServiceimpl {
    private const val BASE_URL = "https://dapi.kakao.com"

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service : KakaoService = retrofit.create(KakaoService::class.java)
}
```

- 싱글톤이란 객체는 하나만 생성하고 프로젝트 어디서나 사용할 수 있게 하는 디자인 패턴중 하나이다.
- 싱글톤 객체로 사용하기 위해 object로 선언한다.
- Retrofit 객체를 생성하여 interface 객체를 넘겨 service라는 실제 구현체를 생성한다. 

##### 2. request data , response data 설계

```kotlin
data class RequestSignUpData(
    val email : String,
    val password : String,
    val userName : String
)
```

- 요청하는 데이터가 있다면 데이터 Type에 맞게 RequsetData를 생성해준다.

```kotlin
data class ResponseSignUpData(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val email: String,
        val password: String,
        val userName: String
    )
}
```

- ResponseData를 생성할 때 Json 객체의 키 값과 타입을 일치 시켜줘야 한다.



##### 3. Call & Callback 등록하여 요청

```kotlin
val call : Call<ResponseKakaoData> = KakaoServiceimpl.service.getWebSearch(et_search.text.toString())
            call.enqueue(object : Callback<ResponseKakaoData>{
                override fun onFailure(call: Call<ResponseKakaoData>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<ResponseKakaoData>,
                    response: Response<ResponseKakaoData>
                ) {
                    response.takeIf { it.isSuccessful }
                        ?.body()
                        ?.let { // 서버 연결 성공
                            kakaoAdpater.data = mutableListOf( KakaoData(removeHTMLTag(it.documents[0].title),removeHTMLTag(it.documents[0].datetime),removeHTMLTag(it.documents[0].contents))
                            )
                            kakaoAdpater.notifyDataSetChanged()
                        }?:let { // 연결 실패
                        showError(response.errorBody())
                    }
                }
                private fun showError(error: ResponseBody?) {
                    val e = error ?: return
                    val ob = JSONObject(e.string())
                    Toast.makeText(view.context,ob.getString("message"), Toast.LENGTH_SHORT).show()
                }
            })
```

- Call<Type> 은 비동기적으로 Type을 받아오는 객체이다. Callback<Type>은 Type 객체를 받아왔을 때 프로그래머의 행동이다.
- 먼저 call 객체를 받아온다. 싱글톤에서 만든 service라는 객체를 이용해서 interface에서 정의한 함수를 사용한다. 요청하는 데이터가 있다면 RequestData에 값을 넣어준다.
- enqueue를 호출하여 실제 서버 통신을 비동기적으로 요청한다. 이 떄, onFailure와 onResponse를 오버라이드 해야한다.
- 서버에 통신이 된다면 onResponse함수에 들어오게 되고, response.isSuccessful 인 경우는 응답이 성공된 경우 즉, Status Code가 200~300일 경우이다.
- 응답이 제대로 이루어지지 않아 body()값이 없는 경우, showError()를 실행한다.


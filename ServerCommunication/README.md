## 3주차 과제
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
위의 코드에서 볼 수 있듯이 Fragment를 관리하기 위해 FragmentManger를 호출해야한다. 

여기서 Fragment Transaction의 역할이 중요하다

-Fragment Transaction 이란?
- Fragment Manager의 역할 중 하나
- 액티비티에서 프래그먼트를 추가,변경,삭제 작업을 수행
- 수행한 트랜잭션의 상태를 백스택에 저장 가능

supportFragmentManager를 이용해 beginTransaction()을 호출하여 추가,제거, 변경등이 가능하다!<br><br>
*****

<br><br>
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
Adpater는 getItem() ,getCount() 이 두가지를 무조건 override 해주어야 한다. 

또한, (fm: FragmentManager) -> FragmentManger를 필요로 한다.

FragmentStatePagerAdapter를 adapter가 상속한다. FragmentStatePagerAdapter 란 보여지는 화면 기준 양 옆 프래그먼트를 제외한 나머지를 완전히 파괴하고, 메모리 누수 관리에 효과적이다. 

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

*****

<br>

#### 3. BottomNavigation
- bottom_menu.xml
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

- selector.xml
```kotlin
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="#86C3BA" android:state_checked="true"/>
    <item android:color="#9E9E9E" android:state_checked="false"/>
</selector>
```

selector를 생성해준다. android:state_checked를 이용해 체크가 될 경우와 안 될 경우의 색을 구분 짓는다.

- BottomNavigation 이벤트
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
네비게이션을 클릭했을 때 index 값을 정해준다. 

여기서 클릭해서 정해진 index가 FragmentAdapter의 position으로 들어가 각각의 Fragment를 호출한다.

*****

#### 4. TabLayout
TabLayout은 상단 탭을 만들 때 주로 사용된다. BottomNavigation에 비해 위치 이동이 자유롭다.

```kotlin
 tl_profile.setupWithViewPager(vp_profile)
        tl_profile.apply{
            getTabAt(0)?.text = "Info"
            getTabAt(1)?.text = "Other"
        }
```
xml에서 정해준 tl_profile(=TabLayout)을 가져와 setupWithViewPager를 호출한다. 

setupWithViewPager를 이용해 vp_profile(=ViewPager)이라는 TabLayout를 이용하기 위한 ViewPager를 연결한다.

getTabAt(0)?.text = "Info" 를 이용해 각 Tab의 타이틀을 정해준다. 

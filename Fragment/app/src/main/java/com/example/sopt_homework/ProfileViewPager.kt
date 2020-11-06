package com.example.sopt_homework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile_view_pager.*
import kotlin.properties.Delegates

class ProfileViewPager : AppCompatActivity() {

    private lateinit var mvp_Adapter: MainViewPagerAdapter
    private lateinit var vp_Adapter : HomeViewPagerAdapter

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

        val profile_name = intent.getStringExtra("name");
        val profile_fg = ProfileFragment()

        val bundle = Bundle()
        bundle.putString("name",profile_name)

        profile_fg.arguments = bundle

        //supportFragmentManager.beginTransaction().add(R.id.fragment_container1,main_fragment).commit()


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


    }
}
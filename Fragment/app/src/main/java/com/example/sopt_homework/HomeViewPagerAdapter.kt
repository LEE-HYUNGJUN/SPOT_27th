package com.example.sopt_homework

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

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
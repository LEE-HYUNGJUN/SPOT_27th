package com.example.sopt_homework.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.sopt_homework.Fragment.PortFragment
import com.example.sopt_homework.Fragment.ProfileFragment
import com.example.sopt_homework.Fragment.SettingFragment

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
package com.example.sopt_homework.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sopt_homework.Adapter.HomeViewPagerAdapter
import com.example.sopt_homework.R
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {

    private lateinit var hvp_adapter: HomeViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var name = arguments?.getString("name").toString()
        tv_name.text=name

        hvp_adapter = HomeViewPagerAdapter(childFragmentManager)
        hvp_adapter.fragments = listOf(
            InfoFragment(),
            OtherFragment()
        )
        vp_profile.adapter = hvp_adapter

        tl_profile.setupWithViewPager(vp_profile)
        tl_profile.apply{
            getTabAt(0)?.text = "Info"
            getTabAt(1)?.text = "Other"
        }
        super.onViewCreated(view, savedInstanceState)
    }

}
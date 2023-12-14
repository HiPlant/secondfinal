package com.example.myapplicationex1.ui.dashboard

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomViewPagerAdapter1 : FragmentStateAdapter {

    constructor(fragment: Fragment) : super(fragment)


    override fun getItemCount(): Int {
        return 3 // 페이지 수에 따라 조정하세요.
    }

    override fun createFragment(position: Int): Fragment {
        // position에 따라 다른 Fragment를 반환하도록 구현하세요.
        return when (position) {
            0 -> FirstFragment()
            1 -> SecondFragment()
            2 -> ThirdFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }

    }

}

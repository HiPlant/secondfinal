package com.example.myapplicationex1.ui.dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomViewPagerAdapter2(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4 // Adjust the number of pages as needed
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> cover1()
            1 -> cover2()
            2 -> cover3()
            3 -> cover4()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    // Define your cover1(), cover2(), cover3(), cover4() functions here
}

package com.example.myapplicationex1.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplicationex1.R
import com.example.myapplicationex1.databinding.FragmentDashboardBinding
import com.example.myapplicationex1.ui.mine.MineFragment
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewPager1: ViewPager2
    private lateinit var recyclerView: RecyclerView
    private lateinit var dotsIndicator: DotsIndicator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view: View = binding.root

        viewPager1 = view.findViewById(R.id.viewPager1)
        recyclerView = view.findViewById(R.id.horizontalRecyclerView)
        dotsIndicator = view.findViewById(R.id.dots_indicator)

        val adapter1 = CustomViewPagerAdapter1(this)
        val adapter2 = CustomRecyclerViewAdapter1(requireContext(), generateImageList())

        viewPager1.adapter = adapter1
        recyclerView.adapter = adapter2
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // ViewPager2 페이지 변경 시 호출되는 콜백 등록
        viewPager1.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDotIndicator(position)
            }
        })

        // 뷰 생성 후 도트 인디케이터 초기화
        updateDotIndicator(0)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun generateImageList(): List<Int> {
        return listOf(
            R.drawable.dose,
            R.drawable.scott,
            R.drawable.mong
            // ... 추가 이미지들을 여기에 추가하세요
        )
    }

    // 도트 인디케이터 업데이트 메서드
    private fun updateDotIndicator(currentPosition: Int) {
        dotsIndicator.setViewPager2(viewPager1)
    }
}

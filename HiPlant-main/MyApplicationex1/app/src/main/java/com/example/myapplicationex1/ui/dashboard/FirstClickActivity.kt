package com.example.myapplicationex1.ui.dashboard

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplicationex1.R
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class FirstClickActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var recyclerView: RecyclerView
    private lateinit var dotsIndicator: DotsIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_click)
        //setupToolbar()
        supportActionBar!!.hide()
        viewPager2 = findViewById(R.id.viewPager2)
        recyclerView = findViewById(R.id.horizontalRecyclerView2)
        dotsIndicator = findViewById(R.id.dotsIndicator2) // 아이디를 맞게 변경

        val pagerAdapter = CustomViewPagerAdapter2(this)
        viewPager2.adapter = pagerAdapter

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        // RecyclerView 어댑터 설정 (CustomRecyclerViewAdapter2는 실제 사용하는 어댑터로 교체하세요)
        val recyclerViewAdapter = CustomRecyclerViewAdapter2(this, generateImageList2())
        recyclerView.adapter = recyclerViewAdapter

        // ViewPager2 페이지 변경 시 호출되는 콜백 등록
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDotIndicator(position)
            }
        })
        val backButton: ImageButton = findViewById(R.id.imageButton)

        backButton.setOnClickListener {
            // 뒤로가기 동작 구현
            onBackPressed()
        }
        // 뷰 생성 후 도트 인디케이터 초기화
        updateDotIndicator(0)
    }

    private fun setupToolbar() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            title = "Today's plant"
            it.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.black)))
        }
    }

    private fun generateImageList2(): List<Int> {
        return listOf(
            R.drawable.rec_plant1,
            R.drawable.rec_plant2,
            R.drawable.rec_plant3
            // 필요한 만큼 이미지를 추가하세요
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // 도트 인디케이터 업데이트 메서드
    private fun updateDotIndicator(currentPosition: Int) {
        dotsIndicator.setViewPager2(viewPager2)
    }
}

package com.example.myapplicationex1.ui.dashboard

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import com.example.myapplicationex1.R

class SecondClickActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_click)
        //setupToolbar()
        supportActionBar!!.hide()

        val backButton: ImageButton = findViewById(R.id.imageButton)

        backButton.setOnClickListener {
            // 뒤로가기 동작 구현
            onBackPressed()
        }
    }
    private fun setupToolbar() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            title = "Today's plant"
            it.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.black)))
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
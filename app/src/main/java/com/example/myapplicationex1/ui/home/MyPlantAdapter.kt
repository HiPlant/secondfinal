package com.example.myapplicationex1.ui.home

import com.example.myapplicationex1.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationex1.MyPlantItem
import com.example.myapplicationex1.PlantsEntity
import com.example.myapplicationex1.TodayPlantItem

class MyPlantAdapter(val itemList: ArrayList<MyPlantItem>) : RecyclerView.Adapter<MyPlantAdapter.MyPlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPlantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_plant_item, parent, false)
        return MyPlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyPlantViewHolder, position: Int) {
        holder.myPlantCategory.setImageResource(itemList[position].categoryImg)
        holder.myPlantEng.text = itemList[position].pEngName
        holder.myPlantNick.text = itemList[position].nickName
        holder.toWatering.setOnClickListener{
            imageClickListener.onClick(it,position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }


    inner class MyPlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myPlantCategory = itemView.findViewById<ImageView>(R.id.MyPlantCategory)
        val myPlantEng = itemView.findViewById<TextView>(R.id.MyPlantEng)
        val myPlantNick = itemView.findViewById<TextView>(R.id.MyPlantNick)
        val toWatering = itemView.findViewById<FrameLayout>(R.id.ToWatering)
    }

    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setImageClickListener(onItemClickListener: OnItemClickListener) {
        this.imageClickListener = onItemClickListener
    }

    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var imageClickListener : OnItemClickListener
}
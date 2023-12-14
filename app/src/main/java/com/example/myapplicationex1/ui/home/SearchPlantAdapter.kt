package com.example.myapplicationex1.ui.home

import com.example.myapplicationex1.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationex1.PlantsEntity
import com.example.myapplicationex1.TodayPlantItem

class SearchPlantAdapter(val itemList: ArrayList<TodayPlantItem>) : RecyclerView.Adapter<SearchPlantAdapter.SearchPlantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPlantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchPlantViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchPlantViewHolder, position: Int) {
        holder.toDescBtn.setImageResource(itemList[position].pImg)
        holder.engPlantName.text = itemList[position].pEngName
        holder.korPlantName.text = itemList[position].pKorName
        holder.isLikePlant.setImageResource(if(itemList[position].isLiked){R.drawable.fill_heart} else{R.drawable.blank_heart})
        holder.itemView.findViewById<ImageView>(R.id.ToDescBtnSearch).setOnClickListener{
            imageClickListener.onClick(it,position)
        }
        holder.itemView.findViewById<ImageView>(R.id.LikeButtonSearch).setOnClickListener{
            heartClickListener.onClick(it,position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }


    inner class SearchPlantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val toDescBtn = itemView.findViewById<ImageView>(R.id.ToDescBtnSearch)
        val engPlantName = itemView.findViewById<TextView>(R.id.EngPlantNameSearch)
        val korPlantName = itemView.findViewById<TextView>(R.id.KorPlantNameSearch)
        val isLikePlant = itemView.findViewById<ImageView>(R.id.LikeButtonSearch)
    }

    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setImageClickListener(onItemClickListener: OnItemClickListener) {
        this.imageClickListener = onItemClickListener
    }
    fun setHeartClickListener(onItemClickListener: OnItemClickListener) {
        this.heartClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var imageClickListener : OnItemClickListener
    private lateinit var heartClickListener: OnItemClickListener
}
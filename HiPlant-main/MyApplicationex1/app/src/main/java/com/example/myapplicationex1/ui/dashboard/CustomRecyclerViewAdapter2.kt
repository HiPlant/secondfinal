package com.example.myapplicationex1.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationex1.R

class CustomRecyclerViewAdapter2(private val context: Context, private val imageList: List<Int>) :
    RecyclerView.Adapter<CustomRecyclerViewAdapter2.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.recycleimage2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_second, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resourceId = imageList[position]
        holder.imageView.setImageResource(resourceId)
    }

    override fun getItemCount() = imageList.size
}

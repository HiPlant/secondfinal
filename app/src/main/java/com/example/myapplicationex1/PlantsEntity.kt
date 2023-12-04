package com.example.myapplicationex1

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_plants")
data class PlantsEntity(
    @PrimaryKey
    val pID: Int,
    val pEngName: String,
    val pKorName: String,
    val pImg: Int,
    val pDesImg: Int,
    val desc: Int,
    val categoryImg: Int,
    val isLiked: Boolean,
    val myDesc: Int = 0,
)

package com.example.myapplicationex1

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "table_my_plants")
data class MyPlantsEntity(
    @PrimaryKey
    val pID: Int,
    val pEngName: String,
    val pImg: Int,
    val pDescImg: Int,
    val desc: Int,
    val categoryImg: Int,
    val nickName: String = "",
    val lastWater: String = "",
    val sun: String = "",
    val temperature: String = ""
)

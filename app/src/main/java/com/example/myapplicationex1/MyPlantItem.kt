package com.example.myapplicationex1

import kotlinx.serialization.Serializable

@Serializable
data class MyPlantItem(val pID: Int,
                       val pEngName: String,
                       val pImg: Int,
                       val pDescImg: Int,
                       val desc: Int,
                       val categoryImg: Int,
                       val nickName: String,
                       val lastWater: String,
                       val sun: String,
                       val temperature: String)

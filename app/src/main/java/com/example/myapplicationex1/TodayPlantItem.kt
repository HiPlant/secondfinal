package com.example.myapplicationex1

import java.io.Serializable

@kotlinx.serialization.Serializable
data class TodayPlantItem(val pID: Int,
                          val pEngName: String,
                          val pKorName: String,
                          val pImg: Int,
                          val pDescImg: Int,
                          val pDesc: Int,
                          val isLiked: Boolean)
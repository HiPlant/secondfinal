package com.example.myapplicationex1

import kotlinx.serialization.Serializable

@Serializable
data class InputData(var nickName: String = "",
                     var lastWater: String = "",
                     var sun: String = "",
                     var temperature: String = "")

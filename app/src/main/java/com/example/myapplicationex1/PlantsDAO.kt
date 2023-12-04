package com.example.myapplicationex1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlantsDAO {
    @Query("SELECT * FROM table_plants")
    fun getAll(): List<PlantsEntity>

    // pID 에 해당하는 selected 값 가져오기
    @Query("Update table_plants Set isLiked = :isLiked WHERE pID = :pID ")
    fun updatePlant(isLiked: Boolean,pID: Int)

    @Query("SELECT isLiked FROM table_plants WHERE pID = :pID")
    fun getIsLiked(pID: Int) : Boolean

    // bookmark 저장 - 중복 값 충돌 발생 시 새로 들어온 데이터로 교체.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBookmark(plantsEntity:PlantsEntity)
}
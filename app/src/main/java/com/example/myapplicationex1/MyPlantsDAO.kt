package com.example.myapplicationex1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyPlantsDAO {
    @Query("SELECT * FROM table_my_plants")
    fun getAll(): List<MyPlantsEntity>

    @Query("SELECT pID FROM table_my_plants WHERE nickName = :nickName ")
    fun getPID(nickName: String): Int

    @Query("SELECT COUNT(*) FROM table_my_plants")
    fun getROWCount(): Int

    // pID 에 해당하는 selected 값 가져오기
    @Query("Update table_my_plants Set nickName = :nickName, lastWater = :lastWater, sun = :sun, temperature = :temperature WHERE pID = :pID ")
    fun updateMyPlant(nickName: String,lastWater: String, sun: String, temperature:String,pID: Int)


    // bookmark 저장 - 중복 값 충돌 발생 시 새로 들어온 데이터로 교체.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMyPlant(myPlantsEntity:MyPlantsEntity)
}
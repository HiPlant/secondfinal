package com.example.myapplicationex1

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MyPlantsEntity::class], version = 8)
abstract class MyPlantsDatabase : RoomDatabase() {
    abstract fun myPlantsDao(): MyPlantsDAO

    // 데이터 베이스 객체를 싱글톤으로 인스턴스.
    companion object {
        private var instance: MyPlantsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MyPlantsDatabase? {
            if (instance == null)
                synchronized(MyPlantsDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MyPlantsDatabase::class.java,
                        "myPlants.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}
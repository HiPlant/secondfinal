package com.example.myapplicationex1

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PlantsEntity::class], version = 3)
abstract class PlantsDatabase : RoomDatabase() {
    abstract fun plantsDao(): PlantsDAO

    // 데이터 베이스 객체를 싱글톤으로 인스턴스.
    companion object {
        private var instance: PlantsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): PlantsDatabase? {
            if (instance == null)
                synchronized(PlantsDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlantsDatabase::class.java,
                        "plants.db"
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
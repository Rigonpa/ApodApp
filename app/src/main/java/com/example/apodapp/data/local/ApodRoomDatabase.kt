package com.example.apodapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apodapp.data.model.ApodResponse

@Database(entities = [ApodResponse::class], version = 1, exportSchema = false)
abstract class ApodRoomDatabase : RoomDatabase() {

    abstract fun apodDao(): ApodDao

    companion object {
        private var instance: ApodRoomDatabase? = null

        fun getInstance(context: Context): ApodRoomDatabase {
            if (instance == null) {

                synchronized(ApodRoomDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ApodRoomDatabase::class.java,
                        "apod_db"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }
    }
}
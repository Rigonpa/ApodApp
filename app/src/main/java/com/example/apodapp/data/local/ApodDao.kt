package com.example.apodapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apodapp.data.model.ApodResponse

@Dao
abstract class ApodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertApod(apodResponse: ApodResponse)

    @Query("SELECT * FROM apod_table")
    abstract fun getApods(): LiveData<List<ApodResponse>>
}
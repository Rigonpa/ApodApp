package com.example.apodapp.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.apodapp.data.local.ApodRoomDatabase
import com.example.apodapp.data.model.ApodResponse

class MainViewModel(private val context: Application): ViewModel() {
    fun getLocalApods() : LiveData<List<ApodResponse>> {
        return ApodRoomDatabase.getInstance(context).apodDao().getApods()
    }
}
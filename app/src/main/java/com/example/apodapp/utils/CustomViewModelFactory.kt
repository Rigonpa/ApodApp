package com.example.apodapp.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apodapp.detail.DetailViewModel
import com.example.apodapp.main.MainViewModel

class CustomViewModelFactory(private val application: Application): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(application)
                isAssignableFrom(MainViewModel::class.java) -> MainViewModel(application)
                else -> throw IllegalArgumentException("Unknown viewmodel")
            }
        } as T
    }
}
package com.example.apodapp.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.apodapp.data.local.ApodRoomDatabase
import com.example.apodapp.data.model.ApodResponse
import com.example.apodapp.data.remote.ApodService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val context: Application): ViewModel() {
    fun getApod(apiKey: String, callback: ApodService.ResponseListener<ApodResponse>) {
        ApodService().apodApi.getApod(apiKey).enqueue(object : Callback<ApodResponse>{
            override fun onResponse(call: Call<ApodResponse>, response: Response<ApodResponse>) {

                if (response.isSuccessful && response.body() != null) {
                    callback.onResponse(response.body()!!)
                } else {
                    callback.onFailure(Throwable(response.message()), response)
                }
            }

            override fun onFailure(call: Call<ApodResponse>, t: Throwable) {
                callback.onFailure(t)
            }

        })
    }

    fun insertApod(apodResponse: ApodResponse) {
        ApodRoomDatabase.getInstance(context).apodDao().insertApod(apodResponse)
    }
}
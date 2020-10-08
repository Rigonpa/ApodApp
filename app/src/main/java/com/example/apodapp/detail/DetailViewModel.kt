package com.example.apodapp.detail

import androidx.lifecycle.ViewModel
import com.example.apodapp.data.model.ApodResponse
import com.example.apodapp.data.network.ApodService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
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
}
package com.example.apodapp.data.network


import com.example.apodapp.data.model.ApodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApodApi {

    @GET("planetary/apod")
    @Headers("Content-Type: application/json")
    fun getApod(@Query("api-key") apiKey: String) : Call<ApodResponse>
}
package com.example.apodapp.data.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApodService {

    interface ResponseListener<T> {
        fun onResponse(response: T)
        fun onFailure(t: Throwable, res: Response<*>? = null)
    }

    val apodApi: ApodApi


    init {

        var retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.nasa.gov")
            .build()

        apodApi = retrofit.create(ApodApi::class.java)

    }
}
package com.example.apodapp.works

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.apodapp.data.local.ApodRoomDatabase
import com.example.apodapp.data.model.ApodResponse
import com.example.apodapp.data.remote.ApodService
import com.example.apodapp.utils.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApodWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {

        ApodService().apodApi.getApod(Common.API_KEY).enqueue(object: Callback<ApodResponse> {
            override fun onFailure(call: Call<ApodResponse>, t: Throwable) {
                // Api error management
            }

            override fun onResponse(call: Call<ApodResponse>, response: Response<ApodResponse>) {
                ApodRoomDatabase.getInstance(applicationContext).apodDao().insertApod(response.body()!!)
            }
        })

        return Result.success()
    }
}
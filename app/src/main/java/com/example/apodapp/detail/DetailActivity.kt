package com.example.apodapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apodapp.R
import com.example.apodapp.data.model.ApodResponse
import com.example.apodapp.data.network.ApodService
import com.example.apodapp.utils.Common
import com.example.apodapp.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private val mViewModel: DetailViewModel by lazy {
        val factory = CustomViewModelFactory()
        ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mViewModel.getApod(Common.API_KEY, object: ApodService.ResponseListener<ApodResponse> {
            override fun onResponse(response: ApodResponse) {
                detailTextView.text = response.explanation
            }

            override fun onFailure(t: Throwable, res: Response<*>?) {
                detailTextView.text = res.toString()
            }
        })
    }
}
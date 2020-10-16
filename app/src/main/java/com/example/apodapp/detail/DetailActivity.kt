package com.example.apodapp.detail

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.apodapp.R
import com.example.apodapp.data.model.ApodResponse
import com.example.apodapp.data.remote.ApodService
import com.example.apodapp.utils.Common
import com.example.apodapp.utils.CustomViewModelFactory
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private var mApodResponse: ApodResponse? = null

    var localApod = false

    private val mViewModel: DetailViewModel by lazy {
        val factory = CustomViewModelFactory(application)
        ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (intent.getStringExtra("ORIGIN_TAG") == "local_apod") { // Se ha pinchado en la lista:
            localApod = true
//            detailSaveApod.text = "DELETE"
            detailSaveApod.setImageResource(android.R.drawable.ic_menu_delete)

            mApodResponse = intent.getSerializableExtra("LOCAL_APOD") as? ApodResponse

            mApodResponse?.let {
                detailTextView.text = it.explanation

                Glide.with(this@DetailActivity)
                    .load(it.url)
                    .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                    .into(detailImageView)
            }

        } else { // Se ha pinchado en la lupa:

//        } else if(intent.getStringExtra("OriginTag") == "remote_apod") {

            mViewModel.getApod(Common.API_KEY, object : ApodService.ResponseListener<ApodResponse> {
                override fun onResponse(response: ApodResponse) {

                    mApodResponse = response

                    detailTextView.text = response.explanation

                    Glide.with(this@DetailActivity)
                        .load(response.url)
                        .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                        .into(detailImageView)

                    // Si lo queremos pasar a grises
//                val colorMatrix = ColorMatrix()
//                colorMatrix.setSaturation(0f)
//                val filter = ColorMatrixColorFilter(colorMatrix)
//                this@DetailActivity.detailImageView.colorFilter = filter
                }

                override fun onFailure(t: Throwable, res: Response<*>?) {
                    detailTextView.text = res.toString()
                }
            })

        }

        detailSaveApod.setOnClickListener {
            if (localApod) { // El elemento ya estaba guardado y queremos eliminarlo de la lista
                mViewModel.deleteApod(mApodResponse!!)
            } else { // Imagen del día queremos añadirla a nuestra base de datos
                mViewModel.insertApod(mApodResponse!!)
            }
            finish()
        }
    }
}
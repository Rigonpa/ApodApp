package com.example.apodapp.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.apodapp.R
import com.example.apodapp.data.model.ApodResponse
import kotlinx.android.synthetic.main.item_list.view.*

interface ItemListInteractorListener {
    fun itemClicked(apodResponse: ApodResponse)
}

class MainAdapter(
    private val context: Context,
    private val items: List<ApodResponse>?,
    private val listener: ItemListInteractorListener
) : RecyclerView.Adapter<MainAdapter.ApodHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ApodHolder(view)
    }

    override fun onBindViewHolder(holder: ApodHolder, position: Int) {
        items?.get(position).let { apod ->
            Glide.with(context)
                .load(apod?.url)
                .apply {
                    RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                }
                .into(holder.itemView.cardImageView)

            holder.itemView.setOnClickListener {
                listener.itemClicked(apod!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    inner class ApodHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var view = itemView
    }
}
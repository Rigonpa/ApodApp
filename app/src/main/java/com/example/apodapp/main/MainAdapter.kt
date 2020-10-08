package com.example.apodapp.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apodapp.R
import kotlinx.android.synthetic.main.item_list.view.*

interface ItemListInteractorListener {
    fun itemClicked()
}

class MainAdapter(
    private val context: Context,
    private val items: ArrayList<String>,
    private val listener: ItemListInteractorListener
) : RecyclerView.Adapter<MainAdapter.ApodHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ApodHolder(view)
    }

    override fun onBindViewHolder(holder: ApodHolder, position: Int) {
        holder.view

        holder.itemView.setOnClickListener {
            listener.itemClicked()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ApodHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var view = itemView
    }
}
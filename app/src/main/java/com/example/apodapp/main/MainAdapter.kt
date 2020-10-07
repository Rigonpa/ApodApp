package com.example.apodapp.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apodapp.R
import kotlinx.android.synthetic.main.item_list.view.*



class MainAdapter(private val context: Context, private val items: ArrayList<String>): RecyclerView.Adapter<MainAdapter.ApodHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ApodHolder(view)
    }

    override fun onBindViewHolder(holder: ApodHolder, position: Int) {
        holder.title = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ApodHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var title: String? = null
        set(value) {
            field = value
            itemView.cardTextView.text = field
        }
    }
}
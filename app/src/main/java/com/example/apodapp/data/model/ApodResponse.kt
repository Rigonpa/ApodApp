package com.example.apodapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*
// @Parcelable
@Entity(tableName = "apod_table")
data class ApodResponse(

	@PrimaryKey
	var id: String = UUID.randomUUID().toString(),

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("media_type")
	val mediaType: String? = null,

	@field:SerializedName("hdurl")
	val hdurl: String? = null,

	@field:SerializedName("service_version")
	val serviceVersion: String? = null,

	@field:SerializedName("explanation")
	val explanation: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null
) : Serializable

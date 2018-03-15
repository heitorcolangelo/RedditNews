package com.heitorcolangelo.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.heitorcolangelo.repository.model.image.Preview
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsData(
    val id: String,
    val title: String,
    val domain: String,
    @SerializedName("num_comments")
    val numComments: String,
    @SerializedName("selftext")
    val selfText: String,
    val thumbnail: String,
    private val preview: Preview?
) : Parcelable {
    fun imageUrl() = preview?.images?.first()?.source?.url
}
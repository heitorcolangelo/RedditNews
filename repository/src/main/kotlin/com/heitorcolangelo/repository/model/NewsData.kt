package com.heitorcolangelo.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsData(
    val id: String,
    val title: String,
    val domain: String,
    @SerializedName("num_comments")
    val numComments: String,
    val thumbnail: String
): Parcelable
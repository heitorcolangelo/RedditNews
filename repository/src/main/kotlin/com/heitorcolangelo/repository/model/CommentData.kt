package com.heitorcolangelo.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentData(
    val id: String,
    val body: String,
    val author: String,
    val permalink: String
) : Parcelable
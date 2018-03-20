package com.heitorcolangelo.repository.model.image

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Preview(
    val images: List<RedditImage>
) : Parcelable

@Parcelize
data class RedditImage(
    val source: ImageSource
) : Parcelable

@Parcelize
data class ImageSource(
    val url: String
) : Parcelable
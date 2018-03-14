package com.heitorcolangelo.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsPage(
    val after: String,
    val children: List<NewsData>
): Parcelable
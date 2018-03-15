package com.heitorcolangelo.repository.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Content(
    val data: ContentData
) : Parcelable
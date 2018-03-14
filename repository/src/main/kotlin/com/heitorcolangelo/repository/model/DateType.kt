package com.heitorcolangelo.repository.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.heitorcolangelo.repository.data.remote.adapter.DateTypeAdapter
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime

@Parcelize
data class DateType(@Expose val localDateTime: LocalDateTime = LocalDateTime.now()) : Parcelable {
    internal val apiFormat: String
        get() = ZonedDateTime.of(localDateTime, ZoneOffset.UTC).format(DateTypeAdapter.API_FORMAT)
}
package com.heitorcolangelo.repository.model

import com.google.gson.annotations.SerializedName

class Page<out T>(
    val after: String?,
    @SerializedName("children")
    val results: List<T> = listOf()
)
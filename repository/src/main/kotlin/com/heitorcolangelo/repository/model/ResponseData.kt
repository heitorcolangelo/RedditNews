package com.heitorcolangelo.repository.model

import com.google.gson.annotations.SerializedName

data class ResponseData<out T>(
    @SerializedName("data")
    val page: Page<T>
)

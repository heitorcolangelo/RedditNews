package com.heitorcolangelo.repository.model

import com.google.gson.annotations.SerializedName

data class NewsResponseData(
    @SerializedName("data")
    val page: NewsPage
)

package com.swapna.metaweather.data.model

import com.google.gson.annotations.SerializedName

data class SearchLocation(
    @SerializedName("title") val title: String,
    @SerializedName("woeid") val woeid: Long
)

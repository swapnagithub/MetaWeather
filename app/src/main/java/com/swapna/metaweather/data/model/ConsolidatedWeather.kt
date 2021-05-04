package com.swapna.metaweather.data.model

data class ConsolidatedWeather (
    val consolidated_weather: List<LocationDetail>,
    val title: String
)
package com.swapna.metaweather.data.network

import com.swapna.metaweather.di.DaggerApiComponent
import com.swapna.metaweather.data.model.ConsolidatedWeather
import com.swapna.metaweather.data.model.SearchLocation

import javax.inject.Inject

class WeatherApiService {

    @Inject
    lateinit var api: WeatherApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    suspend fun getLocationId(location: String) : List<SearchLocation> {
        return api.getLocationId(location)
    }

    suspend fun getLocationDetail(woeid: Long) : ConsolidatedWeather{
        return api.getLocationDetail(woeid)
    }


}
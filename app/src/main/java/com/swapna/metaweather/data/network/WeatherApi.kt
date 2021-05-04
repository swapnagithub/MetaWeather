package com.swapna.metaweather.data.network

import com.swapna.metaweather.data.model.ConsolidatedWeather
import com.swapna.metaweather.data.model.SearchLocation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Weather API communication setup via Retrofit.
 */
interface WeatherApi {
    @GET("location/search/")
    suspend fun getLocationId(
            @Query("query") query: String ): List<SearchLocation>

    @GET("location/{woeid}/")
    suspend fun getLocationDetail(
        @Path("woeid") woeid: Long): ConsolidatedWeather
}
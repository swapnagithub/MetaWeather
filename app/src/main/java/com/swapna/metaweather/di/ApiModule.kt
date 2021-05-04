package com.swapna.metaweather.di

import com.swapna.metaweather.data.network.WeatherApi
import com.swapna.metaweather.data.network.WeatherApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Tells dagger how to provide dependencies from the dependency graph
 */
/**
 * Makes Retrofit api call
 */
@Module
open class ApiModule {
    private val baseUrl = "https://www.metaweather.com/api/"

    @Provides
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }


    @Provides
    open fun provideWeatherApiService(): WeatherApiService {
        return WeatherApiService()
    }
}
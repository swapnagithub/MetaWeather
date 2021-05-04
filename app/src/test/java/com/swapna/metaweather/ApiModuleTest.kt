package com.swapna.metaweather

import com.swapna.metaweather.data.network.WeatherApiService
import com.swapna.metaweather.di.ApiModule

class ApiModuleTest(val mockService: WeatherApiService): ApiModule() {
    override fun provideWeatherApiService(): WeatherApiService {
        return mockService
    }
}

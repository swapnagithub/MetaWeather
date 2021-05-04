package com.swapna.metaweather

import com.swapna.metaweather.data.repository.WeatherRepository
import com.swapna.metaweather.di.AppModule

class AppModuleTest(val mockRepository: WeatherRepository): AppModule() {
    override fun provideWeatherRepo(): WeatherRepository {
        return mockRepository
    }
}
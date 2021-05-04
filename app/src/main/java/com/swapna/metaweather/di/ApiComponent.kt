package com.swapna.metaweather.di

import com.swapna.metaweather.data.network.WeatherApiService
import dagger.Component
import javax.inject.Singleton

/**
 * This component adds info from the ApiModule and lists all classes that can be injected by this component
 */
@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: WeatherApiService)

}
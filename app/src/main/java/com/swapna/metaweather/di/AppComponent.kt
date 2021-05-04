package com.swapna.metaweather.di

import com.swapna.metaweather.data.network.WeatherApiService
import com.swapna.metaweather.data.repository.WeatherRepository
import com.swapna.metaweather.ui.home.MainActivity
import com.swapna.metaweather.ui.home.MainViewModel
import com.swapna.metaweather.ui.weather.WeatherSliderFragment
import com.swapna.metaweather.ui.weather.WeatherSliderViewModel
import dagger.Component
import javax.inject.Singleton

/**
 *AppComponent uses
@Singleton Scope annotation will have a unique instance in this Component
 * This component adds info from the ApiModule and lists all classes that can be injected by this component
 */
@Singleton
@Component(modules = [ApiModule::class, AppModule::class])
interface AppComponent {

    fun inject(viewModel: WeatherSliderViewModel)
    fun inject(mainViewModel: MainViewModel)
    fun inject(weatherRepository: WeatherRepository)
    fun inject(mainActivity: MainActivity)




}
package com.swapna.metaweather.di

import com.swapna.metaweather.data.repository.WeatherRepository
import com.swapna.metaweather.ui.home.MainActivity
import com.swapna.metaweather.ui.home.MainViewModel
import dagger.Module
import dagger.Provides

/**
 * Tells dagger how to provide dependencies from the dependency graph
 */
@Module
open class AppModule {

    @Provides
    open fun provideWeatherRepo() = WeatherRepository()

    @Provides
    open fun provideMainViewModel() = MainViewModel()


}


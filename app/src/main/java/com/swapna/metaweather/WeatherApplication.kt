package com.swapna.metaweather

import android.app.Application


open class WeatherApplication : Application() {

    companion object {
        lateinit var instance: WeatherApplication

    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

    }
}


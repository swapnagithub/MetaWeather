package com.swapna.metaweather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swapna.metaweather.data.model.ConsolidatedWeather
import com.swapna.metaweather.di.DaggerAppComponent
import com.swapna.metaweather.data.repository.WeatherRepository
import com.swapna.metaweather.ui.weather.WeatherSliderFragment
import javax.inject.Inject
/**
 * ViewModel for the [MainActivity] screen.
 * The ViewModel works with the [WeatherRepository] to get the data.
 */
class MainViewModel : ViewModel(){

    @Inject
    lateinit var repository: WeatherRepository
    init {
        DaggerAppComponent.create().inject(this)
    }
    private val _locationNames: MutableLiveData<List<String>> = MutableLiveData()
    val locationNames: LiveData<List<String>> = _locationNames

    init {
        _locationNames.value = repository.locationNames.value
    }





}


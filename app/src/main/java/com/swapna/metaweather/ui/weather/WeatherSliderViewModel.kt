package com.swapna.metaweather.ui.weather

import androidx.lifecycle.*
import com.swapna.metaweather.di.DaggerAppComponent


import com.swapna.metaweather.data.model.ConsolidatedWeather
import com.swapna.metaweather.data.repository.WeatherRepository
import com.swapna.metaweather.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * ViewModel for the [WeatherSliderFragment] screen.
 * The ViewModel works with the [WeatherRepository] to get the data.
 */
class WeatherSliderViewModel(private val locationName: String) : ViewModel() {

//check test dagger injection
    constructor(locationName: String, test: Boolean = true) : this(locationName) {
        injected = true
    }

    @Inject
    lateinit var repository: WeatherRepository
    private var injected = false
    private val weatherResponse = MutableLiveData<Resource<ConsolidatedWeather>>()
    private var selectedLocation: String? = null

    fun inject() {
        if (!injected) {
            DaggerAppComponent.create().inject(this)
        }
    }

    /**
     * Fetches data from api
     * uses ViewModel scope : Coroutine that will be canceled when the ViewModel is cleared.
     */
    private fun loadDataFromRepository() {
        viewModelScope.launch {
            weatherResponse.postValue(Resource.loading(null))
            try {
                val locationList = repository.getLocationId(locationName)
                val locationDetail = repository.getLocationDetail(
                    locationList.first().woeid
                )
                selectedLocation = locationName
                weatherResponse.postValue(Resource.success(locationDetail))
            } catch (e: Exception) {
                weatherResponse.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    /**
     * LiveData tht can be observed by [WeatherSliderFragment]
     */
    fun getWeatherDetails(): LiveData<Resource<ConsolidatedWeather>> {
        return weatherResponse
    }


    fun hardRefresh() {
        inject()
        selectedLocation?.let {
            loadDataFromRepository()
        }
    }

    fun refresh() {
        inject()
        loadDataFromRepository()
    }


    class Factory(private val name: String) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherSliderViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WeatherSliderViewModel(
                    name
                ) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
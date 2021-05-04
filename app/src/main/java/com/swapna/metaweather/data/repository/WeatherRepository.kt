package com.swapna.metaweather.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swapna.metaweather.di.DaggerAppComponent

import com.swapna.metaweather.data.model.ConsolidatedWeather
import com.swapna.metaweather.data.model.SearchLocation
import com.swapna.metaweather.data.network.WeatherApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

/**
 * Repository class that works with remote data source and local cache(future enhancement)
 */
open class WeatherRepository {

    @Inject
    lateinit var apiService: WeatherApiService

    private val locations: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }

/**
 * Currently added Location list as hardcoded value. As an enhancement, I would like to add all
 * locations in local db and can do location search and add them on local db
*/
    private val locationList = listOf(
        "Stockholm",
        "Gothenburg",
        "Mountain View",
        "London",
        "New York",
        "Berlin"
    )


    val locationNames: LiveData<List<String>> = locations

    init {
        DaggerAppComponent.create().inject(this)
        locations.value = locationList
    }


    suspend fun getLocationId(locationName: String): List<SearchLocation> {

        return apiService.getLocationId(locationName)
    }

    suspend fun getLocationDetail(locationWoeid: Long): ConsolidatedWeather {
        return apiService.getLocationDetail(locationWoeid)
    }


}
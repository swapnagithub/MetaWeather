package com.swapna.metaweather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.swapna.metaweather.data.model.ConsolidatedWeather
import com.swapna.metaweather.data.model.LocationDetail
import com.swapna.metaweather.data.model.SearchLocation
import com.swapna.metaweather.data.repository.WeatherRepository
import com.swapna.metaweather.di.DaggerAppComponent
import com.swapna.metaweather.ui.weather.WeatherSliderViewModel
import com.swapna.metaweather.util.Resource
import com.swapna.metaweather.util.TestCoroutineRule
import kotlinx.coroutines.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherSliderViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var mockweatherRepository: WeatherRepository

    var viewModel = WeatherSliderViewModel("Stockholm", true)


    @Mock
    private lateinit var consolidatedWeatherObserver: Observer<Resource<ConsolidatedWeather>>


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        DaggerAppComponent.builder()
            .appModule(AppModuleTest(mockweatherRepository))
            .build()
            .inject(viewModel)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {

        testCoroutineRule.runBlockingTest {
            val locationIdList = listOf(SearchLocation("Stockholm", 906057))
            val weatherDetail = listOf(
                LocationDetail(
                    "Heavy Cloud", "hc", "SE", "2021-05-03",
                    1.625, 10.504999999999999, 10.085, 2.8308432527426497, 1010.0, 44.0
                )
            )
            val consolidatedWeatherTest = ConsolidatedWeather(weatherDetail, "Stockholm")

            doReturn(locationIdList)
                .`when`(mockweatherRepository)
                .getLocationId("Stockholm")

            doReturn(consolidatedWeatherTest)
                .`when`(mockweatherRepository)
                .getLocationDetail(anyLong())

            viewModel.refresh()
            viewModel.getWeatherDetails().observeForever(consolidatedWeatherObserver)
            verify(consolidatedWeatherObserver).onChanged(Resource.success(consolidatedWeatherTest))
            viewModel.getWeatherDetails().removeObserver(consolidatedWeatherObserver)


        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {

            val errorMessage = "Something Went Wrong"
            doThrow(RuntimeException(errorMessage))
                .`when`(mockweatherRepository)
                .getLocationId("Stockholm")

            viewModel.refresh()
            viewModel.getWeatherDetails().observeForever(consolidatedWeatherObserver)
            verify(mockweatherRepository).getLocationId("Stockholm")
            verify(consolidatedWeatherObserver).onChanged(
                Resource.error(
                    RuntimeException(errorMessage).toString(),
                    null
                )
            )
            viewModel.getWeatherDetails().removeObserver(consolidatedWeatherObserver)
        }
    }


    @After
    fun tearDown() {

    }
}
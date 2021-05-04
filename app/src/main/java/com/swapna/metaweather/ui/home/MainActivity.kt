package com.swapna.metaweather.ui.home


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2

import com.swapna.metaweather.R
import com.swapna.metaweather.di.DaggerAppComponent
import kotlinx.android.synthetic.main.activity_main.*
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.swapna.metaweather.ui.weather.WeatherSliderAdapter
import javax.inject.Inject

/**
 * [MainActivity] for main screen
 */
class MainActivity : AppCompatActivity() {


    private lateinit var weatherSliderAdapter: WeatherSliderAdapter

    @Inject
    lateinit var  viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerAppComponent.create().inject(this)

      setUpViewPager()
        observeData()
    }

    private fun setUpViewPager(){
        with(viewPager) {
            offscreenPageLimit = 1
            registerOnPageChangeCallback(onPageChangeCallback)
        }
    }

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {

        }
    }

    private fun observeData() {
        viewModel.locationNames
            .observe(this, Observer {
                it?.let {
                    weatherSliderAdapter =
                        WeatherSliderAdapter(
                            this, it
                        )
                    viewPager.apply {
                        adapter = weatherSliderAdapter
                        offscreenPageLimit = 1
                    }

                    //Used for dot indicator
                    TabLayoutMediator(tab, viewPager) {tab, position ->
                    }.attach()
                }
            })



    }


}




package com.swapna.metaweather.ui.weather

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * WeatherSlider adapter shows individual weather screen
 */
class WeatherSliderAdapter (fa: FragmentActivity,  private val locationList: List<String>) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = locationList.size
    override fun createFragment(position: Int): Fragment {
        return WeatherSliderFragment.newInstance(
            position,
            locationList[position]
        )
    }
}
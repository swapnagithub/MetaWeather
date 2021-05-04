package com.swapna.metaweather.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.swapna.metaweather.R
import com.swapna.metaweather.databinding.FragmentWeatherBinding
import com.swapna.metaweather.di.DaggerAppComponent
import com.swapna.metaweather.util.Status
import kotlinx.android.synthetic.main.fragment_weather.*


class WeatherSliderFragment : Fragment() {

    private val viewModel: WeatherSliderViewModel by lazy {

        ViewModelProvider(this, WeatherSliderViewModel.Factory(getLocationName()))
            .get(WeatherSliderViewModel::class.java)
    }
    private lateinit var dataBinding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_weather,
                container,
                false

            )


        initViews()
        setupObserver()
        return dataBinding.root
    }


    private fun initViews() {
        with(dataBinding) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@WeatherSliderFragment.viewModel
        }
    }

    private fun setupObserver() {
        viewModel.getWeatherDetails().observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE

                    it.data?.let { detail ->
                        detail?.let {
                            dataView.visibility = View.VISIBLE
                            dataBinding.consolidateWeather = it
                            //Second item in the list refers to tomorrow's weather details
                            dataBinding.locationDetail = it.consolidated_weather[1]

                        }
                    }

                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    dataView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    textError.visibility = View.VISIBLE
                    dataView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()

                }
            }
        })

        viewModel.refresh()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshView()
    }


    private fun refreshView() {
        refreshLayout.setOnRefreshListener {
            dataView.visibility = View.GONE
            textError.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            viewModel.hardRefresh()
            refreshLayout.isRefreshing = false
        }
    }


    private fun getLocationName(): String =
        arguments?.getString(EXTRA_LOCATION_NAME)
            ?: throw IllegalArgumentException("$EXTRA_LOCATION_NAME must be provided")

    companion object {

        private val EXTRA_LOCATION_POSITION =
            WeatherSliderFragment::class.java.name + "_EXTRA_LOCATION_POSITION"
        private val EXTRA_LOCATION_NAME =
            WeatherSliderFragment::class.java.name + "_EXTRA_LOCATION_NAME"

        fun newInstance(position: Int, locationName: String) =
            WeatherSliderFragment().apply {
                arguments = Bundle().apply {
                    putInt(EXTRA_LOCATION_POSITION, position)
                    putString(EXTRA_LOCATION_NAME, locationName)
                }
            }
    }


}
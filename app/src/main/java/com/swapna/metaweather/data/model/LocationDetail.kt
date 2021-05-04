package com.swapna.metaweather.data.model

import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt
import kotlin.math.roundToLong

data class LocationDetail(
    @SerializedName("weather_state_name")
    val weatherStateName : String,
    @SerializedName("weather_state_abbr")
    val weatherStateAbbr: String,
    @SerializedName("wind_direction_compass")
    val windDirectionCompass: String,
    @SerializedName("applicable_date")
    val applicableDate: String,
    @SerializedName("min_temp")
    val minTemp: Double,
    @SerializedName("max_temp")
    val maxTemp : Double,
    @SerializedName("the_temp")
    val theTemp : Double,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("air_pressure")
    val airPressure : Double,
    @SerializedName("humidity")
    val humidity: Double
)
{
    val currentTempInString: String
        get() = String.format("%d°c", theTemp.roundToInt())

    val minTempInString: String
        get() = String.format("%d°c", minTemp.roundToInt())

    val maxTempInString: String
        get() = String.format("%d°c", maxTemp.roundToInt())

    val windSpeedInString: String
        get() = "${windSpeed.roundToLong()} km/h"

    val airPressureInString: String
        get() = "${airPressure.roundToLong()} mb"

    val humidityInPercentage: String
        get() = "$humidity%"

    val imageUrl: String
        get() = String.format(
            "https://www.metaweather.com/static/img/weather/png/64/$weatherStateAbbr.png",
            weatherStateAbbr
        )


}
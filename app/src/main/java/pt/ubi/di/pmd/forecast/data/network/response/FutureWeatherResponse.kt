package pt.ubi.di.pmd.forecast.data.network.response

import com.google.gson.annotations.SerializedName
import pt.ubi.di.pmd.forecast.data.db.entity.WeatherLocation

data class FutureWeatherResponse(
    @SerializedName("forecast")
    val futureWeatherEntries: ForecastDaysContainer,
    val location: WeatherLocation
)
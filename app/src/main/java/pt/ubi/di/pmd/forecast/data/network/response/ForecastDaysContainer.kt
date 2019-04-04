package pt.ubi.di.pmd.forecast.data.network.response

import com.google.gson.annotations.SerializedName
import pt.ubi.di.pmd.forecast.data.db.entity.FutureWeatherEntry

data class ForecastDaysContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)
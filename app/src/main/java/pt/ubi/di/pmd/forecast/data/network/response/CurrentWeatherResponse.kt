package pt.ubi.di.pmd.forecast.data.network.response

import com.google.gson.annotations.SerializedName
import pt.ubi.di.pmd.forecast.data.db.entity.CurrentWeatherEntry
import pt.ubi.di.pmd.forecast.data.db.entity.Location

data class CurrentWeatherResponse(
    val location: Location,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)
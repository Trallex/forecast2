package pt.ubi.di.pmd.forecast.data.testCurrent.response

import com.google.gson.annotations.SerializedName

data class WeatherDetails(
    val humidity: Int,
    val pressure: Double,
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
)
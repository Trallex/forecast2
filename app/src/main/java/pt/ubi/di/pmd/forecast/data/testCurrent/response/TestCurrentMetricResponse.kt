package pt.ubi.di.pmd.forecast.data.testCurrent.response

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
//import com.serjltt.moshi.adapters.FirstElement


data class TestCurrentMetricResponse(
    @SerializedName("name")
    val cityName: String,

    val coord: Coord,

//    @FirstElement
//    @Embedded(prefix = "weather_")
    @SerializedName("weather")
    val weatherDescription: List<WeatherDescription>,

    @SerializedName("main")
    val deatails: WeatherDetails,

    val clouds: Clouds,

    val dt: Int,

    val wind: Wind
)
data class Clouds(
    @SerializedName("all")
    val cloudiness: Int
)
data class Wind(
    val deg: Int,
    val speed: Double
)
data class Coord(
    val lat: Double,
    val lon: Double
)
package pt.ubi.di.pmd.forecast.data.network

import androidx.lifecycle.LiveData
import pt.ubi.di.pmd.forecast.data.network.response.CurrentWeatherResponse


interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
        languageCode: String
    )
}
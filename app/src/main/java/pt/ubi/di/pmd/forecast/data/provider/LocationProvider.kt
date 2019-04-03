package pt.ubi.di.pmd.forecast.data.provider

import pt.ubi.di.pmd.forecast.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String

}
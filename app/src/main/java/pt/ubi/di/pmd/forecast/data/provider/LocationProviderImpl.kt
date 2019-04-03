package pt.ubi.di.pmd.forecast.data.provider

import pt.ubi.di.pmd.forecast.data.db.entity.WeatherLocation

class LocationProviderImpl : LocationProvider {
    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        return true
    }

    override suspend fun getPreferredLocationString(): String {
        return "Zabrze"
    }
}
package pt.ubi.di.pmd.forecast.data.repository

import androidx.lifecycle.LiveData
import pt.ubi.di.pmd.forecast.data.db.entity.WeatherLocation
import pt.ubi.di.pmd.forecast.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}
package pt.ubi.di.pmd.forecast.data.repository

import androidx.lifecycle.LiveData
import org.threeten.bp.LocalDate
import pt.ubi.di.pmd.forecast.data.db.entity.WeatherLocation
import pt.ubi.di.pmd.forecast.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry
import pt.ubi.di.pmd.forecast.data.db.unitlocalized.future.UnitSpecificSimpleFutureWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getFutureWeatherList(startDate: LocalDate, metric: Boolean): LiveData<out List<UnitSpecificSimpleFutureWeatherEntry>>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>


}
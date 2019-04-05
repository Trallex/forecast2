package pt.ubi.di.pmd.forecast.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.threeten.bp.LocalDate
import pt.ubi.di.pmd.forecast.data.db.entity.FutureWeatherEntry
import pt.ubi.di.pmd.forecast.data.db.unitlocalized.future.detail.ImperialDeatailFutureWeatherEntry
import pt.ubi.di.pmd.forecast.data.db.unitlocalized.future.detail.MetricDeatailFutureWeatherEntry
import pt.ubi.di.pmd.forecast.data.db.unitlocalized.future.list.ImperialSimpleFutureWeatherEntry
import pt.ubi.di.pmd.forecast.data.db.unitlocalized.future.list.MetricSimpleFutureWeatherEntry

@Dao
interface FutureWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(futureWeatherEntries: List<FutureWeatherEntry>)

    @Query("select * from future_weather where date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastsMetric(startDate: LocalDate): LiveData<List<MetricSimpleFutureWeatherEntry>>

     @Query("select * from future_weather where date(date) >= date(:startDate)")
    fun getSimpleWeatherForecastsImperial(startDate: LocalDate): LiveData<List<ImperialSimpleFutureWeatherEntry>>

    @Query("select * from future_weather where date(date) = date(:date)")
    fun getDetailedWeatherByDateMetric(date: LocalDate): LiveData<MetricDeatailFutureWeatherEntry>

    @Query("select * from future_weather where date(date) = date(:date)")
    fun getDetailedWeatherByDateImperial(date: LocalDate): LiveData<ImperialDeatailFutureWeatherEntry>

    @Query("select count(id) from future_weather where date(date) >= date(:startDate)")
    fun countFutureWeather(startDate: LocalDate): Int

    @Query("delete from future_weather where date(date) < date(:firstDateToKeep)")
    fun deleteOldEntries(firstDateToKeep: LocalDate)
}
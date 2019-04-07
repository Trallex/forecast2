package pt.ubi.di.pmd.forecast.data.testCurrent.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val TEST_CURRENT_WEATHER_ID = 0

@Entity(tableName = "test_current_weather")
data class TestCurrentWeatherEntry(
    val cityName: String,
    val description: String,
    val icon: String,
    val temperature: Double,
    val pressure: Double,
    val humidity: Double,
    val cloudiness: Int,
    val windDir: String,
    val windSpeed: Double

) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = TEST_CURRENT_WEATHER_ID
}
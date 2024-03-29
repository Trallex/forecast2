package pt.ubi.di.pmd.forecast.data.db.unitlocalized.current

import androidx.room.ColumnInfo

data class ImperialCurrentWeatherEntry(
    @ColumnInfo(name="tempF")
     override val temperature: Double,
    @ColumnInfo(name="condition_text")
     override val conditionText: String,
    @ColumnInfo(name="condition_icon")
     override val conditionIconUrl: String,
    @ColumnInfo(name="windMph")
     override val windSpeed: Double,
    @ColumnInfo(name="windDir")
     override val windDirection: String,
    @ColumnInfo(name="pressureIn")
     override val pressureVolume: Double,
    @ColumnInfo(name="feelslikeF")
     override val feelsLikeTemperature: Double,
    @ColumnInfo(name="visMiles")
     override val visibilityDistance: Double

 ): UnitSpecificCurrentWeatherEntry
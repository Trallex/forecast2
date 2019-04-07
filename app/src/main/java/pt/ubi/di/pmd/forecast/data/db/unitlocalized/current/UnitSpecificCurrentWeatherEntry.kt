package pt.ubi.di.pmd.forecast.data.db.unitlocalized.current

interface UnitSpecificCurrentWeatherEntry {
    val temperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val windSpeed: Double
    val windDirection: String
    val pressureVolume: Double
    val feelsLikeTemperature: Double
    val visibilityDistance: Double
}
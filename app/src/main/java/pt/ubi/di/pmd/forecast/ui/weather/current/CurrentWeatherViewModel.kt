package pt.ubi.di.pmd.forecast.ui.weather.current

import androidx.lifecycle.ViewModel;
import pt.ubi.di.pmd.forecast.data.repository.ForecastRepository
import pt.ubi.di.pmd.forecast.internal.UnitSystem
import pt.ubi.di.pmd.forecast.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC //get from the settings l8r
    val isMetric:Boolean
    get() = unitSystem == UnitSystem.METRIC
    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}

package pt.ubi.di.pmd.forecast.ui.weather.current

import androidx.lifecycle.ViewModel;
import pt.ubi.di.pmd.forecast.data.provider.UnitProvider
import pt.ubi.di.pmd.forecast.data.repository.ForecastRepository
import pt.ubi.di.pmd.forecast.internal.UnitSystem
import pt.ubi.di.pmd.forecast.internal.lazyDeferred
import pt.ubi.di.pmd.forecast.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(super.isMetricUnit)
    }

}

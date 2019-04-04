package pt.ubi.di.pmd.forecast.ui.weather.future.list

import androidx.lifecycle.ViewModel;
import org.threeten.bp.LocalDate
import pt.ubi.di.pmd.forecast.data.provider.UnitProvider
import pt.ubi.di.pmd.forecast.data.repository.ForecastRepository
import pt.ubi.di.pmd.forecast.internal.UnitSystem
import pt.ubi.di.pmd.forecast.internal.lazyDeferred
import pt.ubi.di.pmd.forecast.ui.base.WeatherViewModel

class FutureListWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weatherEntries by lazyDeferred{
        forecastRepository.getFutureWeatherList(LocalDate.now(), super.isMetricUnit)
    }

}

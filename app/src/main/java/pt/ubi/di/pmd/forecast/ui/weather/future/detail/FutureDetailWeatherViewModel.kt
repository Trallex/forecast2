package pt.ubi.di.pmd.forecast.ui.weather.future.detail

import androidx.lifecycle.ViewModel;
import org.threeten.bp.LocalDate
import pt.ubi.di.pmd.forecast.data.provider.UnitProvider
import pt.ubi.di.pmd.forecast.data.repository.ForecastRepository
import pt.ubi.di.pmd.forecast.internal.lazyDeferred
import pt.ubi.di.pmd.forecast.ui.base.WeatherViewModel

class FutureDetailWeatherViewModel(
    private val  detailDate: LocalDate,
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeferred {
        forecastRepository.getFutureWeatherByDate(detailDate, super.isMetricUnit)
    }

}

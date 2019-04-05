package pt.ubi.di.pmd.forecast.ui.weather.future.detail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.threeten.bp.LocalDate
import pt.ubi.di.pmd.forecast.data.provider.UnitProvider
import pt.ubi.di.pmd.forecast.data.repository.ForecastRepository


class FutureDetailWeatherViewModelFactory(
    private val detailDate: LocalDate,
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureDetailWeatherViewModel(detailDate, forecastRepository, unitProvider) as T
    }
}
package pt.ubi.di.pmd.forecast.data.network

import pt.ubi.di.pmd.forecast.data.network.response.CurrentWeatherResponse
import pt.ubi.di.pmd.forecast.data.network.response.FutureWeatherResponse
import java.lang.Exception

sealed class ResultCurrent<out T: Any> {
    data class Success<out T : CurrentWeatherResponse?> (val data: T) : ResultCurrent<Any>()
    data class Error( val exception: Exception) : ResultCurrent<Nothing>()
}
sealed class ResultFuture<out T: Any> {
    data class Success<out T : FutureWeatherResponse?> (val data: T) : ResultFuture<Any>()
    data class Error( val exception: Exception) : ResultFuture<Nothing>()
}
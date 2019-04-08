package pt.ubi.di.pmd.forecast.data.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pt.ubi.di.pmd.forecast.data.network.response.CurrentWeatherResponse
import pt.ubi.di.pmd.forecast.data.network.response.FutureWeatherResponse
import java.io.IOException

const val FORECAST_DAYS_COUNT = 7

class WeatherNetworkDataSourceImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
//        try {
//            val fetchedCurrentWeather = apixuWeatherApiService
//                .getCurrentWeather(location, languageCode)
//                .await()
//            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
//        }
//        catch (e: NoConnectivityException){
//            Log.e("Connectivity", "No internet connection", e)
//        }
        if(location != null){
            val response = apixuWeatherApiService.getCurrentWeather(location, languageCode).await()
            if (response.isSuccessful){
                _downloadedCurrentWeather.postValue(ResultCurrent.Success(response.body()).data)
            }
            else{
                //fetchCurrentWeather("Zabrze", languageCode)
                ResultCurrent.Error(IOException("Error occured during fetching data!"))
            }
        }
    }

private val _downloadedFutureWeather = MutableLiveData<FutureWeatherResponse>()
    override val downloadedFutureWeather: LiveData<FutureWeatherResponse>
        get() = _downloadedFutureWeather

    override suspend fun fetchFutureWeather(location: String, languageCode: String) {
//        try {
//            val fetchedFutureWeather = apixuWeatherApiService
//                .getFutureWeather(location, FORECAST_DAYS_COUNT, languageCode)
//                .await()
//            _downloadedFutureWeather.postValue(fetchedFutureWeather)
//        }
//        catch (e: NoConnectivityException){
//            Log.e("Connectivity", "No internet connection", e)
//        }
//    }
        if(location != null){
            val response = apixuWeatherApiService.getFutureWeather(location, FORECAST_DAYS_COUNT, languageCode).await()
            if (response.isSuccessful){
                _downloadedFutureWeather.postValue(ResultFuture.Success(response.body()).data)
            }
            else{
                //fetchCurrentWeather("Zabrze", languageCode)
                ResultCurrent.Error(IOException("Error occured during fetching data!"))
            }
        }
    }



}
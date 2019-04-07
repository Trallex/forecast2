package pt.ubi.di.pmd.forecast.data.testCurrent

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import pt.ubi.di.pmd.forecast.data.network.API_KEY
import pt.ubi.di.pmd.forecast.data.network.ConnectivityInterceptor
import pt.ubi.di.pmd.forecast.data.testCurrent.entity.TestCurrentWeatherEntry
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


const val OW_API_KEY = "5a90e339fa66e67245b1e31aa250f38f"
const val API_URL = "http://api.openweathermap.org/data/2.5/"

interface OpeanWeatherApiService {
    @GET("weather")
    fun getCurentWeather(
        @Query("q") cityName: String?,
        @Query("lang") language: String = "en",
        @Query("units") units: String = "metric",
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null
    ): Deferred<TestCurrentWeatherEntry>

    companion object{
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): OpeanWeatherApiService{
            val requestInterceptor = Interceptor{ chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("APPID", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            //val
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
               // .addConverterFactory(MoshiConventerFactory.create(moshi))
                .build()
                .create(OpeanWeatherApiService::class.java)
        }
    }

}
package pt.ubi.di.pmd.forecast.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import pt.ubi.di.pmd.forecast.data.response.CurrentWeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val API_KEY = "a4e4e3ccb0364a25bf1190213192603"
//http://api.apixu.com/v1/current.json?key=a4e4e3ccb0364a25bf1190213192603&q=Zabrze&lang=en

interface ApixuWeatherApiService {
    @GET("current.json")
    fun getCurrentWeather(
        @Query("q") location: String,
        @Query("leng") languageCode: String = "en"
    ): Deferred<CurrentWeatherResponse>
    companion object{
        operator fun invoke(): ApixuWeatherApiService{
            val requesInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()
                val  request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)

            }

        val okHttpClient  = OkHttpClient.Builder()
            .addInterceptor(requesInterceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.apixu.com/v1/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApixuWeatherApiService::class.java)

        }
    }
}
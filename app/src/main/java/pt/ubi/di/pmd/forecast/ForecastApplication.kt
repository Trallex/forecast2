package pt.ubi.di.pmd.forecast

import android.app.Application
import android.preference.PreferenceManager
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import pt.ubi.di.pmd.forecast.data.db.ForecastDatabase
import pt.ubi.di.pmd.forecast.data.network.*
import pt.ubi.di.pmd.forecast.data.provider.LocationProvider
import pt.ubi.di.pmd.forecast.data.provider.LocationProviderImpl
import pt.ubi.di.pmd.forecast.data.provider.UnitProvider
import pt.ubi.di.pmd.forecast.data.provider.UnitProviderImpl
import pt.ubi.di.pmd.forecast.data.repository.ForecastRepository
import pt.ubi.di.pmd.forecast.data.repository.ForecastRepositoryImpl
import pt.ubi.di.pmd.forecast.ui.weather.current.CurrentWeatherViewModelFactory

class ForecastApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl() }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance(), instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }

}
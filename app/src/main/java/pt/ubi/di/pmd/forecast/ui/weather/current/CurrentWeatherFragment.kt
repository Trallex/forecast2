package pt.ubi.di.pmd.forecast.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

import pt.ubi.di.pmd.forecast.R
import pt.ubi.di.pmd.forecast.data.provider.USE_DEVICE_LOCATION
import pt.ubi.di.pmd.forecast.internal.glide.GlideApp
import pt.ubi.di.pmd.forecast.ui.base.ScopedFragment

class CurrentWeatherFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch{
        val currentWeather = viewModel.weather.await()
        //Log.d("DebugDB-wea", currentWeather.value?.temperature.toString())
        val weatherLocation = viewModel.weatherLocation.await()


        weatherLocation.observe(this@CurrentWeatherFragment, Observer { location ->
            if(location==null) return@Observer
            updateLocation(location.name)

            Log.e("PrefState", PreferenceManager.getDefaultSharedPreferences(context).getBoolean(USE_DEVICE_LOCATION, false).toString())
        })
        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if(it==null) return@Observer

            group_loading.visibility = View.GONE
            updateDateToToday()
            updateTemperatures(it.temperature, it.feelsLikeTemperature)
            updateCondition(it.conditionText)
            updatePressure(it.pressureVolume)
            updateWind(it.windDirection, it.windSpeed)
            updateVisibilty(it.visibilityDistance)

            GlideApp.with(this@CurrentWeatherFragment)
                .load("https:${it.conditionIconUrl}")
                .into(imageView_condition_icon)

        })
    }

    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String):String{
        return if(viewModel.isMetricUnit) metric else imperial
    }
    private fun updateLocation(location: String){
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }
    private fun updateDateToToday(){
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }
    private fun updateTemperatures(temperature: Double, feelsLike: Double){
       val unitAbbreviation =  chooseLocalizedUnitAbbreviation("°C", "°F")
        textView_temperature.text = "$temperature$unitAbbreviation"
        textView_feels_like_temperature.text = "Feels like $feelsLike$unitAbbreviation"
    }
    private fun updateCondition(condition: String){
        textView_condition.text = condition
    }
    private fun updatePressure(pressureVolume: Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("mb", "in")
        textView_pressure.text = "Pressure: $pressureVolume $unitAbbreviation"
    }
    private fun updateWind(windDirection: String, windSpeed: Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("kph", "mph")
        textView_wind.text = "Wind: $windDirection, $windSpeed $unitAbbreviation"
    }
    private fun updateVisibilty(visibilityDistance: Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("km", "mi.")
        textView_visibility.text = "Visibility: $visibilityDistance $unitAbbreviation"
    }
}

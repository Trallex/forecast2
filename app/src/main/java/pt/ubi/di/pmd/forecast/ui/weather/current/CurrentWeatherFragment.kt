package pt.ubi.di.pmd.forecast.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import pt.ubi.di.pmd.forecast.R
import pt.ubi.di.pmd.forecast.data.ApixuWeatherApiService

class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel
        val apiService = ApixuWeatherApiService()

        GlobalScope.launch(Dispatchers.Main) {
            val currentWeatherResponse = apiService.getCurrentWeather("Zabrze").await()
            textView.text = currentWeatherResponse.currentWeatherEntry.toString()
        }
    }

}

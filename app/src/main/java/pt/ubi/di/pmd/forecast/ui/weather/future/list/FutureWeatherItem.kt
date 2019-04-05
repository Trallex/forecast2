package pt.ubi.di.pmd.forecast.ui.weather.future.list

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_future_weather.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import pt.ubi.di.pmd.forecast.R
import pt.ubi.di.pmd.forecast.data.db.unitlocalized.future.list.MetricSimpleFutureWeatherEntry
import pt.ubi.di.pmd.forecast.data.db.unitlocalized.future.list.UnitSpecificSimpleFutureWeatherEntry
import pt.ubi.di.pmd.forecast.internal.glide.GlideApp

class FutureWeatherItem(
    val weatherEntry: UnitSpecificSimpleFutureWeatherEntry
):Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
       viewHolder.apply {
           textView_condition.text = weatherEntry.conditionText
           updateDate()
           updateTemperature()
           updateConditionImage()
       }
    }

    override fun getLayout() = R.layout.item_future_weather
    private fun ViewHolder.updateDate(){
        val dtFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        textView_date.text = weatherEntry.date.format(dtFormatter)
    }
    private fun ViewHolder.updateTemperature(){
        val unitAbbreviation = if(weatherEntry is MetricSimpleFutureWeatherEntry) "°C"
        else "°F"
        textView_temperature.text = "${weatherEntry.avgTemperature}$unitAbbreviation"
    }
    private fun ViewHolder.updateConditionImage(){
        GlideApp.with(this.containerView)
            .load("http:"+weatherEntry.conditionIconUrl)
            .into(imageView_condition_icon)
    }

}
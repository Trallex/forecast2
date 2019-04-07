package pt.ubi.di.pmd.forecast.data.testCurrent.response

import com.google.gson.annotations.SerializedName

data class WeatherDescription(
    val description: String,
    val icon: String,

    @SerializedName("main")
    val condition: String,

    @SerializedName("id")
    val conditionId: Int
)
package pt.ubi.di.pmd.forecast.data.provider

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import pt.ubi.di.pmd.forecast.internal.UnitSystem

const val UNIT_SYSTEM = "UNIT_SYSTEM"

class UnitProviderImpl (context: Context): UnitProvider {

    private val appContext= context.applicationContext
    private val prefernces: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun getUnitSystem(): UnitSystem {
        val selectedName = prefernces.getString(UNIT_SYSTEM, UnitSystem.METRIC.name)
        return UnitSystem.valueOf(selectedName!!)
    }
}
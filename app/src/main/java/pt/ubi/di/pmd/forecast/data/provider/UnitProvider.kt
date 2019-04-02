package pt.ubi.di.pmd.forecast.data.provider

import pt.ubi.di.pmd.forecast.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}
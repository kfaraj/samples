package com.kfaraj.samples.dark.theme

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

/**
 * Demonstrates how to implement a dark theme.
 */
@Suppress("unused")
class DarkThemeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setNightMode()
    }

    private fun setNightMode() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val mode = prefs.getInt(Settings.NIGHT_MODE, Settings.MODE_NIGHT_DEFAULT)
        AppCompatDelegate.setDefaultNightMode(mode)
    }

}

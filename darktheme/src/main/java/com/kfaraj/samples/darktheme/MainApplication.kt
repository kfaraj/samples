package com.kfaraj.samples.darktheme

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

/**
 * Demonstrates how to implement a dark theme.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceManager.setDefaultValues(this, R.xml.preferences, true)
        val nightMode = Settings.getNightMode(this)
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

}

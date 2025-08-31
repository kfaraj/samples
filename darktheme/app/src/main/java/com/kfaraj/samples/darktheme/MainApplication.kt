package com.kfaraj.samples.darktheme

import android.app.Application
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.kfaraj.samples.darktheme.data.Settings

/**
 * Demonstrates how to implement a dark theme.
 */
class MainApplication : Application(),
    OnSharedPreferenceChangeListener {

    override fun onCreate() {
        super.onCreate()
        PreferenceManager.setDefaultValues(this, R.xml.preferences, true)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.registerOnSharedPreferenceChangeListener(this)
        onSharedPreferenceChanged(prefs, null)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        val nightMode = getNightMode(sharedPreferences)
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

    /**
     * Returns the night mode.
     */
    private fun getNightMode(prefs: SharedPreferences): Int {
        val theme = prefs.getString(
            Settings.THEME,
            Settings.THEME_SYSTEM_DEFAULT
        )
        return when (theme) {
            Settings.THEME_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            Settings.THEME_DARK -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
    }

}

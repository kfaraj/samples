package com.kfaraj.samples.darktheme

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

/**
 * Contains settings.
 */
object Settings {

    private const val THEME = "theme"
    private const val THEME_LIGHT = "light"
    private const val THEME_DARK = "dark"
    private const val THEME_DEFAULT = "default"

    /**
     * Returns the night mode.
     */
    fun getNightMode(context: Context): Int {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return when (prefs.getString(THEME, THEME_DEFAULT)) {
            THEME_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            THEME_DARK -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
    }

}

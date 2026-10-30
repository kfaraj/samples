package com.kfaraj.samples.darktheme.data

import android.content.Context
import android.content.SharedPreferences

/**
 * Contains settings.
 */
object Settings {

    /**
     * The theme.
     */
    const val KEY_THEME = "theme"

    /**
     * The theme is light.
     */
    const val THEME_LIGHT = "light"

    /**
     * The theme is dark.
     */
    const val THEME_DARK = "dark"

    /**
     * The theme is system default.
     */
    const val THEME_SYSTEM_DEFAULT = "system_default"

    /**
     * Gets a [SharedPreferences] instance that points to the default file that is used by the
     * preference framework in the given context.
     */
    fun getDefaultSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "${context.packageName}_preferences",
            Context.MODE_PRIVATE
        )
    }

}

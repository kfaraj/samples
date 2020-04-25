package com.kfaraj.samples.dark.theme

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

/**
 * Contains settings.
 */
object Settings {

    /**
     * The night mode.
     */
    const val NIGHT_MODE = "night_mode"

    /**
     * Night mode which uses the recommended default option.
     */
    val MODE_NIGHT_DEFAULT = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    } else {
        AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
    }

}

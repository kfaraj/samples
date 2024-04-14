package com.kfaraj.samples.darktheme.domain

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.kfaraj.samples.darktheme.data.Settings
import com.kfaraj.samples.darktheme.data.SettingsRepository

/**
 * Returns the night mode.
 */
class GetNightModeUseCase(
    context: Context
) {

    private val settingsRepository = SettingsRepository.getInstance(context)

    operator fun invoke(): Int {
        val theme = settingsRepository.getTheme(
            Settings.THEME_DEFAULT
        )
        return when (theme) {
            Settings.THEME_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            Settings.THEME_DARK -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
    }

}

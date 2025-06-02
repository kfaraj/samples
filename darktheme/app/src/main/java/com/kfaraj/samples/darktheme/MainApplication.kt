package com.kfaraj.samples.darktheme

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kfaraj.samples.darktheme.data.SettingsRepository
import com.kfaraj.samples.darktheme.domain.GetNightModeUseCase

/**
 * Demonstrates how to implement a dark theme.
 */
class MainApplication : Application() {

    private lateinit var getNightModeUseCase: GetNightModeUseCase

    override fun onCreate() {
        super.onCreate()
        val settingsRepository = SettingsRepository.getInstance(this)
        getNightModeUseCase = GetNightModeUseCase(settingsRepository)
        val nightMode = getNightModeUseCase()
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

}

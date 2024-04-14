package com.kfaraj.samples.darktheme

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kfaraj.samples.darktheme.domain.GetNightModeUseCase

/**
 * Demonstrates how to implement a dark theme.
 */
class MainApplication : Application() {

    private lateinit var getNightModeUseCase: GetNightModeUseCase

    override fun onCreate() {
        super.onCreate()
        getNightModeUseCase = GetNightModeUseCase(this)
        val nightMode = getNightModeUseCase()
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

}

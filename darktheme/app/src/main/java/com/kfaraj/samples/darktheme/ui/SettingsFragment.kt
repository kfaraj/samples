package com.kfaraj.samples.darktheme.ui

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceFragmentCompat
import com.kfaraj.samples.darktheme.R
import com.kfaraj.samples.darktheme.data.SettingsRepository
import com.kfaraj.samples.darktheme.domain.GetNightModeUseCase
import com.kfaraj.samples.darktheme.util.applyWindowInsetsPadding

/**
 * Contains settings.
 */
class SettingsFragment : PreferenceFragmentCompat(),
    OnSharedPreferenceChangeListener {

    private lateinit var getNightModeUseCase: GetNightModeUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = requireContext()
        val settingsRepository = SettingsRepository.getInstance(context)
        getNightModeUseCase = GetNightModeUseCase(settingsRepository)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView.applyWindowInsetsPadding(
            WindowInsetsCompat.Type.systemBars(),
            applyBottom = true
        )
    }

    override fun onStart() {
        super.onStart()
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onStop() {
        super.onStop()
        preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        val nightMode = getNightModeUseCase()
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

}

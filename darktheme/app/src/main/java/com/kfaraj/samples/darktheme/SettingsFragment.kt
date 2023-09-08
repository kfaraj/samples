package com.kfaraj.samples.darktheme

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceFragmentCompat
import com.kfaraj.samples.darktheme.util.applyWindowInsetsPadding

/**
 * Contains settings.
 */
class SettingsFragment : PreferenceFragmentCompat(),
    OnSharedPreferenceChangeListener {

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
        val context = requireContext()
        val nightMode = Settings.getNightMode(context)
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

}

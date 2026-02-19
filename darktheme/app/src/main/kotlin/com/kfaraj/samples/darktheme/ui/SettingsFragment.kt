package com.kfaraj.samples.darktheme.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceFragmentCompat
import com.kfaraj.samples.darktheme.R
import com.kfaraj.samples.darktheme.util.applyWindowInsetsPadding

/**
 * Contains settings.
 */
class SettingsFragment : PreferenceFragmentCompat() {

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

}

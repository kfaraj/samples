package com.kfaraj.samples.darktheme.ui

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.kfaraj.samples.darktheme.R
import com.kfaraj.samples.darktheme.util.applyWindowInsetsPadding

/**
 * Displays the settings UI state on the screen.
 */
class SettingsFragment : PreferenceFragmentCompat(),
    OnClickListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appBarLayout = ViewCompat.requireViewById<AppBarLayout>(view, R.id.app_bar_layout)
        val toolbar = ViewCompat.requireViewById<MaterialToolbar>(view, R.id.toolbar)
        appBarLayout.applyWindowInsetsPadding(
            WindowInsetsCompat.Type.systemBars() or
                    WindowInsetsCompat.Type.displayCutout(),
            applyLeft = true,
            applyTop = true,
            applyRight = true
        )
        toolbar.setTitle(R.string.settings)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationContentDescription(R.string.navigate_up)
        toolbar.setNavigationOnClickListener(this)
        listView.applyWindowInsetsPadding(
            WindowInsetsCompat.Type.systemBars(),
            applyBottom = true
        )
    }

    override fun onClick(v: View) {
        val activity = requireActivity()
        activity.onNavigateUp()
    }

}

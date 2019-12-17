package com.kfaraj.samples.dark.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Demonstrates how to implement a dark theme.
 */
class DarkThemeActivity : AppCompatActivity() {

    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dark_theme)
        setSupportActionBar(findViewById(R.id.toolbar))
        initFab()
    }

    private fun initFab() {
        fab = findViewById(R.id.fab)
        val mode = AppCompatDelegate.getDefaultNightMode()
        initFab(mode)
    }

    private fun initFab(mode: Int) {
        when (mode) {
            AppCompatDelegate.MODE_NIGHT_NO -> {
                fab.setImageResource(R.drawable.ic_mode_night_no_black_24dp)
                fab.setOnClickListener { setNightMode(AppCompatDelegate.MODE_NIGHT_YES) }
            }
            AppCompatDelegate.MODE_NIGHT_YES -> {
                fab.setImageResource(R.drawable.ic_mode_night_yes_black_24dp)
                fab.setOnClickListener { setNightMode(Settings.MODE_NIGHT_DEFAULT) }
            }
            else -> {
                fab.setImageResource(R.drawable.ic_mode_night_default_black_24dp)
                fab.setOnClickListener { setNightMode(AppCompatDelegate.MODE_NIGHT_NO) }
            }
        }
    }

    private fun setNightMode(mode: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        prefs.edit().putInt(Settings.NIGHT_MODE, mode).apply()
        AppCompatDelegate.setDefaultNightMode(mode)
        initFab(mode)
    }

}

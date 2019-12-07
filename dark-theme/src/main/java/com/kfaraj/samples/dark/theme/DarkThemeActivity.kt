package com.kfaraj.samples.dark.theme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Demonstrates how to implement a dark theme.
 */
class DarkThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dark_theme)
        setSupportActionBar(findViewById(R.id.toolbar))
    }

}

package com.kfaraj.samples.darktheme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.commit
import com.google.android.material.appbar.MaterialToolbar

/**
 * Contains settings.
 */
class SettingsActivity : AppCompatActivity(R.layout.activity_settings) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = ActivityCompat.requireViewById<MaterialToolbar>(this, R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            val fragment = SettingsFragment.newInstance()
            supportFragmentManager.commit {
                replace(R.id.container, fragment)
                setReorderingAllowed(true)
            }
        }
    }

}

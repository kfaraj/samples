package com.kfaraj.samples.darktheme

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.kfaraj.samples.darktheme.ui.SettingsFragment

/**
 * Contains the [SettingsFragment].
 */
class SettingsActivity : AppCompatActivity(R.layout.activity_settings) {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add<SettingsFragment>(R.id.container)
                setReorderingAllowed(true)
            }
        }
    }

}

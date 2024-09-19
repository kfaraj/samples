package com.kfaraj.samples.darktheme

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.kfaraj.samples.darktheme.util.applyWindowInsetsPadding

/**
 * Contains settings.
 */
class SettingsActivity : AppCompatActivity(R.layout.activity_settings),
    OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val toolbar = ActivityCompat.requireViewById<MaterialToolbar>(this, R.id.toolbar)
        toolbar.setNavigationOnClickListener(this)
        (toolbar.parent as AppBarLayout).applyWindowInsetsPadding(
            WindowInsetsCompat.Type.systemBars() or
                    WindowInsetsCompat.Type.displayCutout(),
            applyLeft = true,
            applyTop = true,
            applyRight = true
        )
    }

    override fun onClick(v: View) {
        onNavigateUp()
    }

}

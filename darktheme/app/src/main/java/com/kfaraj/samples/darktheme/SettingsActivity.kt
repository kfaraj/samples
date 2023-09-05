package com.kfaraj.samples.darktheme

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.appbar.MaterialToolbar

/**
 * Contains settings.
 */
class SettingsActivity : AppCompatActivity(R.layout.activity_settings),
    OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = ActivityCompat.requireViewById<MaterialToolbar>(this, R.id.toolbar)
        toolbar.setNavigationOnClickListener(this)
    }

    override fun onClick(v: View) {
        onNavigateUp()
    }

}

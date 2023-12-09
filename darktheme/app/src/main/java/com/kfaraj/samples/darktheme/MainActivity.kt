package com.kfaraj.samples.darktheme

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.kfaraj.samples.darktheme.util.applyWindowInsetsMargin

/**
 * Demonstrates how to implement a dark theme.
 */
class MainActivity : AppCompatActivity(R.layout.activity_main),
    OnMenuItemClickListener,
    OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val toolbar = ActivityCompat.requireViewById<MaterialToolbar>(this, R.id.toolbar)
        val fab = ActivityCompat.requireViewById<FloatingActionButton>(this, R.id.fab)
        toolbar.setOnMenuItemClickListener(this)
        toolbar.applyWindowInsetsMargin(
            WindowInsetsCompat.Type.systemBars(),
            applyLeft = true,
            applyRight = true
        )
        fab.setOnClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return if (item.itemId == R.id.settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        } else {
            false
        }
    }

    override fun onClick(v: View) {
        Snackbar.make(v, R.string.lorem_ipsum, Snackbar.LENGTH_SHORT)
            .setAnchorView(R.id.fab)
            .show()
    }

}

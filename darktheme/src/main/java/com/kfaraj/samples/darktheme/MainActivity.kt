package com.kfaraj.samples.darktheme

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.commit
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

/**
 * Demonstrates how to implement a dark theme.
 */
class MainActivity : AppCompatActivity(R.layout.activity_main),
    OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar = ActivityCompat.requireViewById<MaterialToolbar>(this, R.id.toolbar)
        val fab = ActivityCompat.requireViewById<FloatingActionButton>(this, R.id.fab)
        setSupportActionBar(toolbar)
        fab.setOnClickListener(this)
        if (savedInstanceState == null) {
            val fragment = MainFragment.newInstance()
            supportFragmentManager.commit {
                replace(R.id.container, fragment)
                setReorderingAllowed(true)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View) {
        Snackbar.make(v, R.string.lorem_ipsum, Snackbar.LENGTH_SHORT)
            .show()
    }

}

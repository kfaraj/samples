package com.kfaraj.samples.darktheme.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.kfaraj.samples.darktheme.R
import com.kfaraj.samples.darktheme.SettingsActivity
import com.kfaraj.samples.darktheme.util.applyWindowInsetsPadding

/**
 * Displays the main UI state on the screen.
 */
class MainFragment : Fragment(R.layout.fragment_main),
    OnMenuItemClickListener,
    OnClickListener {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appBarLayout = ViewCompat.requireViewById<AppBarLayout>(view, R.id.app_bar_layout)
        val toolbar = ViewCompat.requireViewById<MaterialToolbar>(view, R.id.toolbar)
        val scrollView = ViewCompat.requireViewById<NestedScrollView>(view, R.id.scroll_view)
        val fab = ViewCompat.requireViewById<FloatingActionButton>(view, R.id.fab)
        appBarLayout.applyWindowInsetsPadding(
            WindowInsetsCompat.Type.systemBars() or
                    WindowInsetsCompat.Type.displayCutout(),
            applyLeft = true,
            applyTop = true,
            applyRight = true
        )
        toolbar.setOnMenuItemClickListener(this)
        scrollView.applyWindowInsetsPadding(
            WindowInsetsCompat.Type.systemBars(),
            applyBottom = true
        )
        fab.setOnClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        return if (item.itemId == R.id.settings) {
            val context = requireContext()
            val intent = Intent(context, SettingsActivity::class.java)
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

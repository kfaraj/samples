package com.kfaraj.samples.darktheme.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.kfaraj.samples.darktheme.R
import com.kfaraj.samples.darktheme.util.applyWindowInsetsPadding

/**
 * Demonstrates how to implement a dark theme.
 */
class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.applyWindowInsetsPadding(
            WindowInsetsCompat.Type.systemBars(),
            applyBottom = true
        )
    }

}

package com.kfaraj.samples.pokedex

import androidx.test.core.app.launchActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    @Test
    fun launchActivity() {
        launchActivity<MainActivity>()
    }

}

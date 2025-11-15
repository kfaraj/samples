package com.kfaraj.samples.koin

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.OrderWith
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Alphanumeric

@OrderWith(Alphanumeric::class)
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun launchActivity() {
        launchActivity<MainActivity>()
    }

    @Test
    fun relaunchActivity() {
        launchActivity<MainActivity>()
    }

}

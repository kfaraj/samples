package com.kfaraj.samples.koin.robolectric

import android.app.Application
import org.koin.core.annotation.KoinApplication
import org.koin.plugin.module.dsl.startKoin

@KoinApplication
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin<MainApplication>()
    }

}

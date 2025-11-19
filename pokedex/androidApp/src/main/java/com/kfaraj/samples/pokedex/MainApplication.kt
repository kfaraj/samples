package com.kfaraj.samples.pokedex

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinApplication
import org.koin.ksp.generated.startKoin
import org.koin.mp.KoinPlatformTools

/**
 * Demonstrates best practices for Modern Android Development.
 */
@KoinApplication
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (KoinPlatformTools.defaultContext().getOrNull() == null) {
            startKoin {
                androidContext(this@MainApplication)
            }
        }
    }

}

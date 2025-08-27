package com.kfaraj.samples.pokedex

import android.app.Application
import com.kfaraj.samples.pokedex.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import org.koin.mp.KoinPlatformTools

/**
 * Demonstrates best practices for Modern Android Development.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (KoinPlatformTools.defaultContext().getOrNull() == null) {
            startKoin {
                androidContext(this@MainApplication)
                modules(AppModule().module)
            }
        }
    }

}

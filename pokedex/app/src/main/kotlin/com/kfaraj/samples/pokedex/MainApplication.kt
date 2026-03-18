package com.kfaraj.samples.pokedex

import android.app.Application
import com.kfaraj.samples.pokedex.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.KoinApplication
import org.koin.core.context.stopKoin
import org.koin.ksp.generated.startKoin

/**
 * Demonstrates best practices for Modern Android Development.
 */
@KoinApplication(modules = [AppModule::class])
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}

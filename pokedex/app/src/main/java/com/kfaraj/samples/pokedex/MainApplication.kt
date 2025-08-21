package com.kfaraj.samples.pokedex

import android.app.Application
import com.kfaraj.samples.pokedex.di.CoroutinesModule
import com.kfaraj.samples.pokedex.di.DatabaseModule
import com.kfaraj.samples.pokedex.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import org.koin.ksp.generated.module

/**
 * Demonstrates best practices for Modern Android Development.
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(
                this@MainApplication
            )
            modules(
                defaultModule,
                CoroutinesModule.module,
                NetworkModule.module,
                DatabaseModule.module
            )
        }
    }

}

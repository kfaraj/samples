package com.kfaraj.samples.pokedex

import android.app.Application
import com.kfaraj.samples.pokedex.shared.di.CoroutinesModule
import com.kfaraj.samples.pokedex.shared.di.DatabaseModule
import com.kfaraj.samples.pokedex.shared.di.NetworkModule
import com.kfaraj.samples.pokedex.shared.di.SharedModule
import com.kfaraj.samples.pokedex.shared.di.DatabaseBuilderModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
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
                SharedModule.module,
                CoroutinesModule.module,
                NetworkModule.module,
                DatabaseModule.module,
                DatabaseBuilderModule.module
            )
        }
    }

}

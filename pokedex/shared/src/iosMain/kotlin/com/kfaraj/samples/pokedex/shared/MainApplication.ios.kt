package com.kfaraj.samples.pokedex.shared

import com.kfaraj.samples.pokedex.shared.di.AppModule
import org.koin.core.annotation.KoinApplication
import org.koin.plugin.module.dsl.startKoin

/**
 * Maintains global application state.
 */
@KoinApplication(modules = [AppModule::class])
public object MainApplication {

    /**
     * Starts Koin with modules discovered from [MainApplication].
     */
    public fun startKoin() {
        startKoin<MainApplication>()
    }

}

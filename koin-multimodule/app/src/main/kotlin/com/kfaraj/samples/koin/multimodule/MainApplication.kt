package com.kfaraj.samples.koin.multimodule

import android.app.Application
import org.koin.core.annotation.KoinApplication
import org.koin.plugin.module.dsl.startKoin

@KoinApplication(modules = [AppModule::class])
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin<MainApplication>()
    }

}

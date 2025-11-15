package com.kfaraj.samples.koin

import android.app.Application
import org.koin.core.annotation.KoinApplication
import org.koin.ksp.generated.startKoin

@KoinApplication
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

}

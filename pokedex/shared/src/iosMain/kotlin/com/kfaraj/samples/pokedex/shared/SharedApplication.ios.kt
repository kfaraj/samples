package com.kfaraj.samples.pokedex.shared

import com.kfaraj.samples.pokedex.shared.di.SharedModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import org.koin.mp.KoinPlatformTools

/**
 * TODO
 */
object SharedApplication {

    /**
     * TODO
     */
    fun startKoin() {
        if (KoinPlatformTools.defaultContext().getOrNull() == null) {
            startKoin {
                modules(SharedModule().module)
            }
        }
    }

}

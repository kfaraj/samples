package com.kfaraj.samples.pokedex.shared

import com.kfaraj.samples.pokedex.shared.di.CoroutinesModule
import com.kfaraj.samples.pokedex.shared.di.DatabaseModule
import com.kfaraj.samples.pokedex.shared.di.NetworkModule
import com.kfaraj.samples.pokedex.shared.di.SharedModule
import org.koin.core.annotation.KoinApplication
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import org.koin.mp.KoinPlatformTools

@KoinApplication
object SharedApplication {

    /**
     * TODO
     */
    fun startKoin() {
        if (KoinPlatformTools.defaultContext().getOrNull() == null) {
            startKoin {
                modules(
                    CoroutinesModule.module,
                    DatabaseModule.module,
                    NetworkModule.module,
                    SharedModule.module
                )
            }
        }
    }

}

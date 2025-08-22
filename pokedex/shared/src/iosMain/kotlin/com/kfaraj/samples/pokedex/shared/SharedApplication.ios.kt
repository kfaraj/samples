package com.kfaraj.samples.pokedex.shared

import com.kfaraj.samples.pokedex.shared.di.CoroutinesModule
import com.kfaraj.samples.pokedex.shared.di.DatabaseModule
import com.kfaraj.samples.pokedex.shared.di.NetworkModule
import com.kfaraj.samples.pokedex.shared.di.SharedModule
import com.kfaraj.samples.pokedex.shared.di.SqlModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

object SharedApplication {

    fun startKoin() {
        startKoin {
            modules(
                SharedModule.module,
                CoroutinesModule.module,
                NetworkModule.module,
                DatabaseModule.module,
                SqlModule.module
            )
        }
    }

}

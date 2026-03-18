package com.kfaraj.samples.pokedex.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

/**
 * Provides bindings for the app.
 */
@Module(includes = [CoroutinesModule::class, DatabaseModule::class, NetworkModule::class])
@ComponentScan("com.kfaraj.samples.pokedex")
object AppModule

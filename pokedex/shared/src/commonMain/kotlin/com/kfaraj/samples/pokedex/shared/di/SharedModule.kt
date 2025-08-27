package com.kfaraj.samples.pokedex.shared.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

/**
 * Provides default bindings.
 */
@ComponentScan("**")
@Module([CoroutinesModule::class, NetworkModule::class, DatabaseModule::class])
class SharedModule

package com.kfaraj.samples.pokedex.shared.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

/**
 * Provides bindings for the shared module.
 */
@ComponentScan("com.kfaraj.samples.pokedex.shared")
@Module
object SharedModule

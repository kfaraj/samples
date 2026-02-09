package com.kfaraj.samples.pokedex.shared.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

/**
 * Provides shared bindings for the app.
 */
@Module
@Configuration
@ComponentScan("com.kfaraj.samples.pokedex.shared")
object SharedModule

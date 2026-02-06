package com.kfaraj.samples.pokedex.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

/**
 * Provides bindings for the app.
 */
@Module
@Configuration
@ComponentScan("com.kfaraj.samples.pokedex")
object AppModule

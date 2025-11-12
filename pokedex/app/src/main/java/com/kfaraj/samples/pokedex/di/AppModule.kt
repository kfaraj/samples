package com.kfaraj.samples.pokedex.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

/**
 * Provides default bindings.
 */
@ComponentScan("com.kfaraj.samples.pokedex")
@Module
@Configuration
object AppModule

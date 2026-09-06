package com.kfaraj.samples.pokedex.data.pokemon.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

/**
 * Provides bindings for the data.
 */
@Module(includes = [DatabaseModule::class, NetworkModule::class])
@ComponentScan("com.kfaraj.samples.pokedex.data.pokemon")
public object PokemonDataModule

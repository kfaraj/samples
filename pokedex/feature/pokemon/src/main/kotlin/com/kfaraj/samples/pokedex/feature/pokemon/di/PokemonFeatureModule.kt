package com.kfaraj.samples.pokedex.feature.pokemon.di

import com.kfaraj.samples.pokedex.data.pokemon.di.PokemonDataModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

/**
 * Provides bindings for the feature.
 */
@Module(includes = [PokemonDataModule::class])
@ComponentScan("com.kfaraj.samples.pokedex.feature.pokemon")
public object PokemonFeatureModule

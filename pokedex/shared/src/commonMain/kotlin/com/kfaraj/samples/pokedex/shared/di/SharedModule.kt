package com.kfaraj.samples.pokedex.shared.di

import com.kfaraj.samples.pokedex.feature.pokemon.di.PokemonFeatureModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

/**
 * Provides bindings for the app.
 */
@Module(includes = [PokemonFeatureModule::class])
@ComponentScan("com.kfaraj.samples.pokedex.shared")
public object SharedModule

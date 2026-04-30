package com.kfaraj.samples.pokedex.shared

import com.kfaraj.samples.pokedex.shared.di.SharedModule
import org.koin.core.annotation.KoinApplication

/**
 * Maintains global application state.
 */
@KoinApplication(modules = [SharedModule::class])
public object SharedApplication

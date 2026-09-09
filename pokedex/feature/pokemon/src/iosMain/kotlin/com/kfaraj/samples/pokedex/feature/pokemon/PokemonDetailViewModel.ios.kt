package com.kfaraj.samples.pokedex.feature.pokemon

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

/**
 * Instantiates [PokemonDetailViewModel].
 */
public object PokemonDetailViewModelFactory : KoinComponent {

    /**
     * Creates a new instance of [PokemonDetailViewModel].
     */
    public fun create(
        key: PokemonDetailKey
    ): PokemonDetailViewModel {
        return get {
            parametersOf(key)
        }
    }

}

package com.kfaraj.samples.pokedex.feature.pokemon

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * Instantiates [PokemonListViewModel].
 */
public object PokemonListViewModelFactory : KoinComponent {

    /**
     * Creates a new instance of [PokemonListViewModel].
     */
    public fun create(
    ): PokemonListViewModel {
        return get()
    }

}

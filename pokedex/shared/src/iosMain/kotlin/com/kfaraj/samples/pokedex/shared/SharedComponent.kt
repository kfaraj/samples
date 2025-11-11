package com.kfaraj.samples.pokedex.shared

import com.kfaraj.samples.pokedex.shared.data.PokemonsRepository
import com.kfaraj.samples.pokedex.shared.ui.PokedexViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

/**
 * TODO
 */
object SharedComponent : KoinComponent {

    /**
     * TODO
     */
    fun getPokedexViewModel(): PokedexViewModel {
        return get()
    }

    /**
     * TODO
     */
    fun getPokemonsRepository(): PokemonsRepository {
        return get()
    }

}

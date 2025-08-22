package com.kfaraj.samples.pokedex.shared

import com.kfaraj.samples.pokedex.shared.data.PokemonsRepository
import com.kfaraj.samples.pokedex.shared.ui.PokedexViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object SharedComponent : KoinComponent {

    fun getPokedexViewModel(): PokedexViewModel {
        return get()
    }

    fun getPokemonsRepository(): PokemonsRepository {
        return get()
    }

}

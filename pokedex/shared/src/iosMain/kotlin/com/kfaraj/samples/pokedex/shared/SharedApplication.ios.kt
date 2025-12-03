package com.kfaraj.samples.pokedex.shared

import androidx.lifecycle.SavedStateHandle
import com.kfaraj.samples.pokedex.shared.ui.PokedexViewModel
import com.kfaraj.samples.pokedex.shared.ui.PokemonViewModel
import org.koin.core.annotation.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

@KoinApplication
object SharedApplication : KoinComponent {

    fun getPokedexViewModel(): PokedexViewModel {
        return get()
    }

    fun getPokemonViewModel(id: Int): PokemonViewModel {
        val savedStateHandle = SavedStateHandle(mapOf("id" to id))
        return get { parametersOf(savedStateHandle) }
    }

}

package com.kfaraj.samples.pokedex.shared

import androidx.lifecycle.SavedStateHandle
import com.kfaraj.samples.pokedex.shared.ui.PokemonDetailViewModel
import com.kfaraj.samples.pokedex.shared.ui.PokemonListViewModel
import org.koin.core.annotation.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

@KoinApplication
object SharedApplication : KoinComponent {

    fun getPokemonListViewModel(): PokemonListViewModel {
        return get()
    }

    fun getPokemonDetailViewModel(id: Int): PokemonDetailViewModel {
        val savedStateHandle = SavedStateHandle(mapOf("id" to id))
        return get { parametersOf(savedStateHandle) }
    }

}

package com.kfaraj.samples.pokedex.shared.ui

import androidx.lifecycle.SavedStateHandle
import com.kfaraj.samples.pokedex.shared.data.Pokemon
import com.kfaraj.samples.pokedex.shared.data.PokemonsRepository
import com.kfaraj.samples.pokedex.shared.testutils.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PokemonViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun uiState() = runTest {
        val savedStateHandle = SavedStateHandle(mapOf("id" to 1))
        val pokemonsRepository = mock<PokemonsRepository>().apply {
            whenever(get(1)).thenReturn(BULBASAUR)
        }
        val viewModel = PokemonViewModel(
            savedStateHandle,
            pokemonsRepository
        )
        val result = viewModel.uiState.value
        assertEquals(BULBASAUR_UI_STATE, result)
    }

    companion object {
        private val BULBASAUR = Pokemon(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
        private val BULBASAUR_UI_STATE = PokemonUiState(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
    }

}

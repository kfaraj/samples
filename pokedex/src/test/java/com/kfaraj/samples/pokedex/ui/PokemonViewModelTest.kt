package com.kfaraj.samples.pokedex.ui

import androidx.lifecycle.SavedStateHandle
import com.kfaraj.samples.pokedex.data.Pokemon
import com.kfaraj.samples.pokedex.data.PokemonsRepository
import com.kfaraj.samples.pokedex.domain.GetSpriteUseCase
import com.kfaraj.samples.pokedex.testutils.MainDispatcherRule
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
        val getSpriteUseCase = mock<GetSpriteUseCase>().apply {
            whenever(invoke(1)).thenReturn("/1.png")
        }
        val viewModel = PokemonViewModel(
            savedStateHandle,
            pokemonsRepository,
            getSpriteUseCase
        )
        val result = viewModel.uiState.value
        assertEquals(BULBASAUR_UI_STATE, result)
    }

    companion object {
        private val BULBASAUR = Pokemon(1, "bulbasaur")
        private val BULBASAUR_UI_STATE = PokemonUiState(1, "bulbasaur", "/1.png")
    }

}

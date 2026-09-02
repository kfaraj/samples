package com.kfaraj.samples.pokedex.feature.pokemon

import com.kfaraj.samples.pokedex.data.pokemon.Pokemon
import com.kfaraj.samples.pokedex.data.pokemon.PokemonRepository
import com.kfaraj.samples.pokedex.feature.pokemon.testutils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PokemonDetailViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun uiState() = runTest {
        val key = PokemonDetailKey(1)
        val pokemonRepository = mockk<PokemonRepository> {
            coEvery { get(1) } returns BULBASAUR
        }
        val viewModel = PokemonDetailViewModel(
            key,
            pokemonRepository
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
        private val BULBASAUR_UI_STATE = PokemonDetailUiState(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
    }

}

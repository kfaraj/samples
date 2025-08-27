package com.kfaraj.samples.pokedex.shared.ui

/**
 * Contains the Pokémon UI state.
 */
sealed class PokemonUiState {

    data object Loading : PokemonUiState()

    data class Success(
        val id: Int,
        val name: String,
        val sprite: String
    ) : PokemonUiState()

    data class Error(
        val message: String?
    ) : PokemonUiState()

}

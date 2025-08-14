package com.kfaraj.samples.pokedex.shared.ui

import com.kfaraj.samples.pokedex.shared.data.Pokemon
import com.kfaraj.samples.pokedex.shared.data.PokemonsRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.io.IOException

/**
 * Exposes the Pokémon UI state.
 */
class PokemonViewModel(
    id: Int,
    pokemonsRepository: PokemonsRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<PokemonUiState> =
        MutableStateFlow(viewModelScope, PokemonUiState.Loading)

    /**
     * The stream of Pokémon UI state.
     */
    @NativeCoroutinesState
    val uiState: StateFlow<PokemonUiState> =
        _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = try {
                pokemonsRepository.get(id).toPokemonUiState()
            } catch (e: IOException) {
                PokemonUiState.Error(e.message)
            } catch (e: ResponseException) {
                PokemonUiState.Error(e.message)
            }
        }
    }

    /**
     * Converts the model from the data layer to the UI layer.
     */
    private fun Pokemon.toPokemonUiState(): PokemonUiState {
        return PokemonUiState.Success(
            id,
            name,
            sprite
        )
    }

}

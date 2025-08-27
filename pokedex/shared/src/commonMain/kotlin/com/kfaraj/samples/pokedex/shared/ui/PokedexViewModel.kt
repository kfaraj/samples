package com.kfaraj.samples.pokedex.shared.ui

import com.kfaraj.samples.pokedex.shared.data.Pokemon
import com.kfaraj.samples.pokedex.shared.data.PokemonsRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import org.koin.android.annotation.KoinViewModel

/**
 * Exposes the Pokédex UI state.
 */
@KoinViewModel
class PokedexViewModel(
    pokemonsRepository: PokemonsRepository
) : ViewModel() {

    private val _pagingData: MutableStateFlow<List<PokedexItemUiState>> =
        MutableStateFlow(viewModelScope, emptyList())

    /**
     * The stream of paged Pokédex items UI states.
     */
    @NativeCoroutinesState
    val pagingData: StateFlow<List<PokedexItemUiState>> =
        _pagingData.asStateFlow()

    init {
        viewModelScope.launch {
            pokemonsRepository.getPagingDataStream(PAGE_SIZE)
                .map { pagingData ->
                    pagingData.map { pokemon ->
                        pokemon.toPokedexItemUiState()
                    }
                }.collect { pagingData ->
                    _pagingData.value = pagingData
                }
        }
    }

    /**
     * Converts the model from the data layer to the UI layer.
     */
    private fun Pokemon.toPokedexItemUiState(): PokedexItemUiState {
        return PokedexItemUiState(
            id,
            name,
            sprite
        )
    }

    companion object {
        private const val PAGE_SIZE = 50
    }

}

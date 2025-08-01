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
import kotlinx.coroutines.flow.update

/**
 * Exposes the Pokédex UI state.
 */
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
            var items: List<PokedexItemUiState>
            do {
                items = pokemonsRepository.getPagingDataStream(
                    limit = PAGE_SIZE,
                    offset = _pagingData.value.size
                ).map { pokemon ->
                    pokemon.toPokedexItemUiState()
                }
                _pagingData.update {
                    it + items
                }
            } while (items.isNotEmpty())
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

package com.kfaraj.samples.pokedex.shared.ui

import com.kfaraj.samples.pokedex.shared.data.Pokemon
import com.kfaraj.samples.pokedex.shared.data.PokemonsRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokedexViewModel(
    pokemonsRepository: PokemonsRepository,
    defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
    viewModelScope: CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)
) {

    private val _pagingData: MutableStateFlow<List<Pokemon>> = MutableStateFlow(emptyList())

    @NativeCoroutines
    val pagingData: StateFlow<List<Pokemon>> = _pagingData.asStateFlow()

    constructor(
        pokemonsRepository: PokemonsRepository,
        defaultDispatcher: CoroutineDispatcher
    ) : this(
        pokemonsRepository = pokemonsRepository,
        defaultDispatcher = defaultDispatcher,
        viewModelScope = CoroutineScope(SupervisorJob() + defaultDispatcher)
    )

    constructor(
        pokemonsRepository: PokemonsRepository
    ) : this(
        pokemonsRepository = pokemonsRepository,
        defaultDispatcher = Dispatchers.Default
    )

    init {
        viewModelScope.launch {
            var pokemons: List<Pokemon>
            do {
                pokemons = pokemonsRepository.getPokemons(
                    limit = 20,
                    offset = _pagingData.value.size
                )
                _pagingData.update {
                    it + pokemons
                }
            } while (pokemons.isNotEmpty())
        }
    }

}

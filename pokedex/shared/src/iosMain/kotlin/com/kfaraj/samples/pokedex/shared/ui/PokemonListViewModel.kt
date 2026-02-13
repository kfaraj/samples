package com.kfaraj.samples.pokedex.shared.ui

import com.kfaraj.samples.pokedex.shared.paging.LazyPagingItems

val PokemonListViewModel.lazyPagingItems: LazyPagingItems<PokemonListItemUiState>
    get() = LazyPagingItems(viewModelScope, pagingData)

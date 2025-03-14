package com.kfaraj.samples.pokedex.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.kfaraj.samples.pokedex.domain.FormatIdUseCase
import com.kfaraj.samples.pokedex.domain.FormatNameUseCase
import com.kfaraj.samples.pokedex.ui.theme.AppTheme
import kotlinx.coroutines.flow.flowOf

/**
 * Displays the Pokédex UI state on the screen.
 */
@Composable
fun PokedexScreen(
    viewModel: PokedexViewModel,
    formatIdUseCase: FormatIdUseCase,
    formatNameUseCase: FormatNameUseCase,
    onClick: (item: PokedexItemUiState?) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyPagingItems = viewModel.pagingData.collectAsLazyPagingItems()
    PokedexScreen(
        lazyPagingItems = lazyPagingItems,
        formatIdUseCase = formatIdUseCase,
        formatNameUseCase = formatNameUseCase,
        onClick = onClick,
        modifier = modifier
    )
}

@Composable
private fun PokedexScreen(
    lazyPagingItems: LazyPagingItems<PokedexItemUiState>,
    formatIdUseCase: FormatIdUseCase,
    formatNameUseCase: FormatNameUseCase,
    onClick: (item: PokedexItemUiState?) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.id }
        ) { index ->
            val item = lazyPagingItems[index]
            PokedexItem(
                onClick = { onClick(item) },
                media = item?.sprite,
                title = item?.id?.let { formatIdUseCase(it) } ?: "",
                body = item?.name?.let { formatNameUseCase(it) } ?: ""
            )
        }
    }
}

@Preview
@Composable
private fun PokedexScreenPreview() {
    AppTheme {
        PokedexScreen(
            lazyPagingItems = flowOf(
                PagingData.from(
                    listOf(
                        PokedexItemUiState(
                            id = 1,
                            name = "Bulbasaur",
                            sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                        )
                    )
                )
            ).collectAsLazyPagingItems(),
            formatIdUseCase = FormatIdUseCase(),
            formatNameUseCase = FormatNameUseCase(),
            onClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

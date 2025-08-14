package com.kfaraj.samples.pokedex.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.kfaraj.samples.pokedex.R
import com.kfaraj.samples.pokedex.shared.ui.PokedexItemUiState
import com.kfaraj.samples.pokedex.ui.theme.AppTheme

/**
 * Displays the Pokédex item UI state on the screen.
 */
@Composable
fun PokedexItem(
    item: PokedexItemUiState?,
    onItemClick: (item: PokedexItemUiState?) -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = {
            onItemClick(item)
        },
        modifier = modifier
    ) {
        Column {
            AsyncImage(
                model = item?.sprite,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.medium
                    )
            )
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = item?.id?.let { stringResource(R.string.number, it) } ?: "",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = item?.name ?: "",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun PokedexItemPreview() {
    AppTheme {
        PokedexItem(
            item = PokedexItemUiState(
                id = 1,
                name = "Bulbasaur",
                sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
            ),
            onItemClick = {}
        )
    }
}

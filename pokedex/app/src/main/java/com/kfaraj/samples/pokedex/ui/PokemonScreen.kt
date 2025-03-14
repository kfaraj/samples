package com.kfaraj.samples.pokedex.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.kfaraj.samples.pokedex.domain.FormatIdUseCase
import com.kfaraj.samples.pokedex.domain.FormatNameUseCase
import com.kfaraj.samples.pokedex.ui.theme.AppTheme

/**
 * Displays the Pokémon UI state on the screen.
 */
@Composable
fun PokemonScreen(
    viewModel: PokemonViewModel,
    formatIdUseCase: FormatIdUseCase,
    formatNameUseCase: FormatNameUseCase,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    PokemonScreen(
        uiState = uiState.value,
        formatIdUseCase = formatIdUseCase,
        formatNameUseCase = formatNameUseCase,
        modifier = modifier
    )
}

@Composable
private fun PokemonScreen(
    uiState: PokemonUiState,
    formatIdUseCase: FormatIdUseCase,
    formatNameUseCase: FormatNameUseCase,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = uiState.sprite,
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
                text = uiState.id?.let { formatIdUseCase(it) } ?: "",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = uiState.name?.let { formatNameUseCase(it) } ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview
@Composable
private fun PokemonScreenPreview() {
    AppTheme {
        PokemonScreen(
            uiState = PokemonUiState(
                id = 1,
                name = "Bulbasaur",
                sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
            ),
            formatIdUseCase = FormatIdUseCase(),
            formatNameUseCase = FormatNameUseCase(),
            modifier = Modifier.fillMaxSize()
        )
    }
}

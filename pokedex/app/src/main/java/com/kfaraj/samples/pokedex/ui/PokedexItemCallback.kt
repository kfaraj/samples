package com.kfaraj.samples.pokedex.ui

import androidx.recyclerview.widget.DiffUtil

/**
 * Calculates the diff between two non-null Pokédex items UI states in a list.
 */
class PokedexItemCallback : DiffUtil.ItemCallback<PokedexItemUiState>() {

    override fun areItemsTheSame(
        oldItem: PokedexItemUiState,
        newItem: PokedexItemUiState
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PokedexItemUiState,
        newItem: PokedexItemUiState
    ): Boolean {
        return oldItem == newItem
    }

}

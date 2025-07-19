package com.kfaraj.samples.pokedex.ui

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 * Displays the paged Pokédex items UI states on the screen.
 */
class PokedexItemAdapter(
    diffCallback: DiffUtil.ItemCallback<PokedexItemUiState>,
    private val onItemClick: (v: View, item: PokedexItemUiState?) -> Unit
) : PagingDataAdapter<PokedexItemUiState, PokedexItemViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexItemViewHolder {
        return PokedexItemViewHolder(
            parent
        ) { v, position ->
            val item = getItem(position)
            onItemClick(v, item)
        }
    }

    override fun onBindViewHolder(holder: PokedexItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

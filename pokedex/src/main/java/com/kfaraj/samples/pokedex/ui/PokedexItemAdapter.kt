package com.kfaraj.samples.pokedex.ui

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.kfaraj.samples.pokedex.domain.FormatIdUseCase
import com.kfaraj.samples.pokedex.domain.FormatNameUseCase

/**
 * Displays the paged Pokédex items UI states on the screen.
 */
class PokedexItemAdapter(
    diffCallback: DiffUtil.ItemCallback<PokedexItemUiState>,
    private val formatIdUseCase: FormatIdUseCase,
    private val formatNameUseCase: FormatNameUseCase
) : PagingDataAdapter<PokedexItemUiState, PokedexItemViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexItemViewHolder {
        return PokedexItemViewHolder(parent, formatIdUseCase, formatNameUseCase)
    }

    override fun onBindViewHolder(holder: PokedexItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

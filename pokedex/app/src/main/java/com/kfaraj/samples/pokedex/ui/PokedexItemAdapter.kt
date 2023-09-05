package com.kfaraj.samples.pokedex.ui

import android.view.View
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
    private val formatNameUseCase: FormatNameUseCase,
    private val onClick: (v: View, item: PokedexItemUiState?) -> Unit
) : PagingDataAdapter<PokedexItemUiState, PokedexItemViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexItemViewHolder {
        return PokedexItemViewHolder(
            parent,
            formatIdUseCase,
            formatNameUseCase
        ) { v, position ->
            val item = getItem(position)
            onClick(v, item)
        }
    }

    override fun onBindViewHolder(holder: PokedexItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

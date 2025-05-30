package com.kfaraj.samples.pokedex.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.allowHardware
import com.kfaraj.samples.pokedex.R
import com.kfaraj.samples.pokedex.domain.FormatIdUseCase
import com.kfaraj.samples.pokedex.domain.FormatNameUseCase

/**
 * Displays the Pokédex item UI state on the screen.
 */
class PokedexItemViewHolder(
    parent: ViewGroup,
    private val formatIdUseCase: FormatIdUseCase,
    private val formatNameUseCase: FormatNameUseCase,
    onClick: (v: View, position: Int) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
) {

    private val mediaView = ViewCompat.requireViewById<ImageView>(itemView, R.id.media)
    private val titleView = ViewCompat.requireViewById<TextView>(itemView, R.id.title)
    private val bodyView = ViewCompat.requireViewById<TextView>(itemView, R.id.body)

    init {
        itemView.setOnClickListener { v ->
            onClick(v, bindingAdapterPosition)
        }
    }

    /**
     * Binds the [item] with the view.
     */
    fun bind(item: PokedexItemUiState?) {
        itemView.transitionName = item?.id?.toString()
        mediaView.load(item?.sprite) {
            allowHardware(false)
        }
        titleView.text = item?.id?.let { formatIdUseCase(it) }
        bodyView.text = item?.name?.let { formatNameUseCase(it) }
    }

}

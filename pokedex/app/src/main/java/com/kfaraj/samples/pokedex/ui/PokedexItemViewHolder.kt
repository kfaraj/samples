package com.kfaraj.samples.pokedex.ui

import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.RecyclerView
import com.kfaraj.samples.pokedex.domain.FormatIdUseCase
import com.kfaraj.samples.pokedex.domain.FormatNameUseCase
import com.kfaraj.samples.pokedex.ui.theme.AppTheme

/**
 * Displays the Pokédex item UI state on the screen.
 */
class PokedexItemViewHolder(
    parent: ViewGroup,
    private val formatIdUseCase: FormatIdUseCase,
    private val formatNameUseCase: FormatNameUseCase,
    onClick: (v: View, position: Int) -> Unit
) : RecyclerView.ViewHolder(
    ComposeView(parent.context)
) {

    private val composeView = itemView as ComposeView

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
        composeView.setContent {
            AppTheme {
                PokedexItem(
                    onClick = { itemView.performClick() },
                    media = item?.sprite,
                    title = item?.id?.let { formatIdUseCase(it) } ?: "",
                    body = item?.name?.let { formatNameUseCase(it) } ?: ""
                )
            }
        }
    }

}

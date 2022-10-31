package com.kfaraj.samples.pokedex.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.google.android.material.color.MaterialColors
import com.google.android.material.transition.MaterialContainerTransform
import com.kfaraj.samples.pokedex.R
import com.kfaraj.samples.pokedex.domain.FormatIdUseCase
import com.kfaraj.samples.pokedex.domain.FormatNameUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Displays the Pokémon UI state on the screen.
 */
@AndroidEntryPoint
class PokemonFragment : Fragment(R.layout.fragment_pokemon) {

    @Inject
    lateinit var formatIdUseCase: FormatIdUseCase

    @Inject
    lateinit var formatNameUseCase: FormatNameUseCase

    private val viewModel by viewModels<PokemonViewModel>()

    private lateinit var mediaView: ImageView
    private lateinit var titleView: TextView
    private lateinit var bodyView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context = requireContext()
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            setAllContainerColors(
                MaterialColors.getColor(
                    context,
                    android.R.attr.colorBackground,
                    Color.TRANSPARENT
                )
            )
            scrimColor = Color.TRANSPARENT
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mediaView = ViewCompat.requireViewById(view, R.id.media)
        titleView = ViewCompat.requireViewById(view, R.id.title)
        bodyView = ViewCompat.requireViewById(view, R.id.body)
        view.transitionName = "container"
        postponeEnterTransition()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    (view.parent as? ViewGroup)?.doOnPreDraw {
                        startPostponedEnterTransition()
                    }
                    bind(uiState)
                }
            }
        }
    }

    /**
     * Binds the [uiState] with the view.
     */
    private fun bind(uiState: PokemonUiState) {
        Glide.with(this)
            .load(uiState.sprite)
            .into(mediaView)
        titleView.text = uiState.id?.let { formatIdUseCase(it) }
        bodyView.text = uiState.name?.let { formatNameUseCase(it) }
    }

}

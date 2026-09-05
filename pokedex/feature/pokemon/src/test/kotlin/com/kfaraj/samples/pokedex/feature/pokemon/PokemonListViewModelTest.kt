package com.kfaraj.samples.pokedex.feature.pokemon

import androidx.paging.RemoteMediator
import androidx.paging.testing.asPagingSourceFactory
import com.kfaraj.samples.pokedex.data.pokemon.Pokemon
import com.kfaraj.samples.pokedex.data.pokemon.PokemonRepository
import com.kfaraj.samples.pokedex.feature.pokemon.testutils.FakeRemoteMediator
import com.kfaraj.samples.pokedex.feature.pokemon.testutils.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PokemonListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun pagingData() = runTest {
        val pagingSourceFactory = listOf(BULBASAUR).asPagingSourceFactory()
        val pagingSource = pagingSourceFactory()
        val remoteMediator = FakeRemoteMediator<Int, Pokemon>(
            RemoteMediator.MediatorResult.Success(true)
        )
        val pokemonRepository = mockk<PokemonRepository> {
            every { getPagingSource() } returns pagingSource
            every { getRemoteMediator() } returns remoteMediator
        }
        val viewModel = PokemonListViewModel(
            pokemonRepository
        )
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.itemSnapshotList.collect()
        }
        val result = viewModel.itemSnapshotList.value
        assertEquals(listOf(BULBASAUR_UI_STATE), result)
    }

    companion object {
        private val BULBASAUR = Pokemon(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
        private val BULBASAUR_UI_STATE = PokemonListItemUiState(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
    }

}

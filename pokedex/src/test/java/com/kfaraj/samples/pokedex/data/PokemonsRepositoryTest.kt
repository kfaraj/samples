package com.kfaraj.samples.pokedex.data

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingConfig
import com.kfaraj.samples.pokedex.data.local.PokemonEntity
import com.kfaraj.samples.pokedex.data.local.PokemonsLocalDataSource
import com.kfaraj.samples.pokedex.data.remote.PokemonsRemoteDataSource
import com.kfaraj.samples.pokedex.testutils.MainDispatcherRule
import com.kfaraj.samples.pokedex.testutils.TestItemCallback
import com.kfaraj.samples.pokedex.testutils.TestListUpdateCallback
import com.kfaraj.samples.pokedex.testutils.TestPagingSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class PokemonsRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun get() = runTest {
        val pokemonsRemoteDataSource = mock<PokemonsRemoteDataSource>()
        val pokemonsLocalDataSource = mock<PokemonsLocalDataSource>().apply {
            whenever(get(1)).thenReturn(BULBASAUR_ENTITY)
        }
        val pokemonsRepository = PokemonsRepository(
            pokemonsRemoteDataSource,
            pokemonsLocalDataSource
        )
        val result = pokemonsRepository.get(1)
        assertEquals(BULBASAUR, result)
    }

    @Test
    fun getPagingDataStream() = runTest {
        val pokemonsRemoteDataSource = mock<PokemonsRemoteDataSource>()
        val pagingSource = TestPagingSource<Int, PokemonEntity>(listOf(BULBASAUR_ENTITY))
        val pokemonsLocalDataSource = mock<PokemonsLocalDataSource>().apply {
            whenever(getPagingSource()).thenReturn(pagingSource)
            whenever(getCount()).thenReturn(1)
        }
        val pokemonsRepository = PokemonsRepository(
            pokemonsRemoteDataSource,
            pokemonsLocalDataSource
        )
        val result = pokemonsRepository.getPagingDataStream(PagingConfig(1)).first()
        val differ = AsyncPagingDataDiffer(
            TestItemCallback<Pokemon>(),
            TestListUpdateCallback(),
            mainDispatcherRule.testDispatcher,
            mainDispatcherRule.testDispatcher
        )
        val collectJob = launch(mainDispatcherRule.testDispatcher) {
            differ.submitData(result)
        }
        assertEquals(listOf(BULBASAUR), differ.snapshot().items)
        collectJob.cancel()
    }

    companion object {
        private val BULBASAUR_ENTITY = PokemonEntity(1, "bulbasaur")
        private val BULBASAUR = Pokemon(1, "bulbasaur")
    }

}

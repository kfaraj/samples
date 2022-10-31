package com.kfaraj.samples.pokedex.data

import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator.InitializeAction
import androidx.paging.RemoteMediator.MediatorResult
import com.kfaraj.samples.pokedex.data.local.PokemonEntity
import com.kfaraj.samples.pokedex.data.local.PokemonsLocalDataSource
import com.kfaraj.samples.pokedex.data.remote.NamedApiResource
import com.kfaraj.samples.pokedex.data.remote.NamedApiResourceList
import com.kfaraj.samples.pokedex.data.remote.PokemonsRemoteDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PokemonsRemoteMediatorTest {

    @Test
    fun load_refresh() = runTest {
        val response = NamedApiResourceList(1, null, null, listOf(BULBASAUR_API_RESOURCE))
        val pokemonsRemoteDataSource = mock<PokemonsRemoteDataSource>().apply {
            whenever(getPokemon(1, 0)).thenReturn(response)
        }
        val pokemonsLocalDataSource = mock<PokemonsLocalDataSource>().apply {
            whenever(getCount()).thenReturn(0)
        }
        val pokemonsRemoteMediator = PokemonsRemoteMediator(
            pokemonsRemoteDataSource,
            pokemonsLocalDataSource
        )
        val state = PagingState<Int, PokemonEntity>(emptyList(), null, PagingConfig(1), 0)
        val result = pokemonsRemoteMediator.load(LoadType.REFRESH, state)
        verify(pokemonsLocalDataSource).insertAll(listOf(BULBASAUR_ENTITY))
        assertTrue(result is MediatorResult.Success)
        assertTrue((result as MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun load_append() = runTest {
        val response = NamedApiResourceList(1, null, "/", emptyList())
        val pokemonsRemoteDataSource = mock<PokemonsRemoteDataSource>().apply {
            whenever(getPokemon(1, 1)).thenReturn(response)
        }
        val pokemonsLocalDataSource = mock<PokemonsLocalDataSource>().apply {
            whenever(getCount()).thenReturn(1)
        }
        val pokemonsRemoteMediator = PokemonsRemoteMediator(
            pokemonsRemoteDataSource,
            pokemonsLocalDataSource
        )
        val state = PagingState<Int, PokemonEntity>(emptyList(), null, PagingConfig(1), 0)
        val result = pokemonsRemoteMediator.load(LoadType.APPEND, state)
        verify(pokemonsLocalDataSource).insertAll(emptyList())
        assertTrue(result is MediatorResult.Success)
        assertTrue((result as MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun initialize_launchInitialRefresh() = runTest {
        val pokemonsRemoteDataSource = mock<PokemonsRemoteDataSource>()
        val pokemonsLocalDataSource = mock<PokemonsLocalDataSource>().apply {
            whenever(getCount()).thenReturn(0)
        }
        val pokemonsRemoteMediator = PokemonsRemoteMediator(
            pokemonsRemoteDataSource,
            pokemonsLocalDataSource
        )
        val result = pokemonsRemoteMediator.initialize()
        assertEquals(InitializeAction.LAUNCH_INITIAL_REFRESH, result)
    }

    @Test
    fun initialize_skipInitialRefresh() = runTest {
        val pokemonsRemoteDataSource = mock<PokemonsRemoteDataSource>()
        val pokemonsLocalDataSource = mock<PokemonsLocalDataSource>().apply {
            whenever(getCount()).thenReturn(1)
        }
        val pokemonsRemoteMediator = PokemonsRemoteMediator(
            pokemonsRemoteDataSource,
            pokemonsLocalDataSource
        )
        val result = pokemonsRemoteMediator.initialize()
        assertEquals(InitializeAction.SKIP_INITIAL_REFRESH, result)
    }

    companion object {
        private val BULBASAUR_API_RESOURCE = NamedApiResource("bulbasaur", "/1/")
        private val BULBASAUR_ENTITY = PokemonEntity(1, "bulbasaur")
    }

}

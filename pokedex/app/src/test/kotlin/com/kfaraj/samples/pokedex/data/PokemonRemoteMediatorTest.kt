package com.kfaraj.samples.pokedex.data

import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator.InitializeAction
import androidx.paging.RemoteMediator.MediatorResult
import com.kfaraj.samples.pokedex.data.local.PokemonEntity
import com.kfaraj.samples.pokedex.data.local.PokemonLocalDataSource
import com.kfaraj.samples.pokedex.data.remote.NamedApiResource
import com.kfaraj.samples.pokedex.data.remote.NamedApiResourceList
import com.kfaraj.samples.pokedex.data.remote.PokemonRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PokemonRemoteMediatorTest {

    @Test
    fun load_refresh() = runTest {
        val response = NamedApiResourceList(1, null, null, listOf(BULBASAUR_SPECIES_API_RESOURCE))
        val pokemonRemoteDataSource = mockk<PokemonRemoteDataSource> {
            coEvery { getPokemonSpecies(1, 0) } returns response
        }
        val pokemonLocalDataSource = mockk<PokemonLocalDataSource> {
            coEvery { upsertAll(any()) } returns Unit
            coEvery { getCount() } returns 0
        }
        val pokemonRemoteMediator = PokemonRemoteMediator(
            pokemonRemoteDataSource,
            pokemonLocalDataSource
        )
        val state = PagingState<Int, PokemonEntity>(emptyList(), null, PagingConfig(1), 0)
        val result = pokemonRemoteMediator.load(LoadType.REFRESH, state)
        coVerify { pokemonLocalDataSource.upsertAll(listOf(BULBASAUR_ENTITY)) }
        assertTrue(result is MediatorResult.Success)
        assertTrue((result as MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun load_append() = runTest {
        val response = NamedApiResourceList(1, null, "/", emptyList())
        val pokemonRemoteDataSource = mockk<PokemonRemoteDataSource> {
            coEvery { getPokemonSpecies(1, 1) } returns response
        }
        val pokemonLocalDataSource = mockk<PokemonLocalDataSource> {
            coEvery { upsertAll(any()) } returns Unit
            coEvery { getCount() } returns 1
        }
        val pokemonRemoteMediator = PokemonRemoteMediator(
            pokemonRemoteDataSource,
            pokemonLocalDataSource
        )
        val state = PagingState<Int, PokemonEntity>(emptyList(), null, PagingConfig(1), 0)
        val result = pokemonRemoteMediator.load(LoadType.APPEND, state)
        coVerify { pokemonLocalDataSource.upsertAll(emptyList()) }
        assertTrue(result is MediatorResult.Success)
        assertTrue((result as MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun initialize_launchInitialRefresh() = runTest {
        val pokemonRemoteDataSource = mockk<PokemonRemoteDataSource>()
        val pokemonLocalDataSource = mockk<PokemonLocalDataSource> {
            coEvery { getCount() } returns 0
        }
        val pokemonRemoteMediator = PokemonRemoteMediator(
            pokemonRemoteDataSource,
            pokemonLocalDataSource
        )
        val result = pokemonRemoteMediator.initialize()
        assertEquals(InitializeAction.LAUNCH_INITIAL_REFRESH, result)
    }

    @Test
    fun initialize_skipInitialRefresh() = runTest {
        val pokemonRemoteDataSource = mockk<PokemonRemoteDataSource>()
        val pokemonLocalDataSource = mockk<PokemonLocalDataSource> {
            coEvery { getCount() } returns 1
        }
        val pokemonRemoteMediator = PokemonRemoteMediator(
            pokemonRemoteDataSource,
            pokemonLocalDataSource
        )
        val result = pokemonRemoteMediator.initialize()
        assertEquals(InitializeAction.SKIP_INITIAL_REFRESH, result)
    }

    companion object {
        private val BULBASAUR_SPECIES_API_RESOURCE = NamedApiResource(
            "bulbasaur",
            "https://pokeapi.co/api/v2/pokemon-species/1/"
        )
        private val BULBASAUR_ENTITY = PokemonEntity(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
    }

}

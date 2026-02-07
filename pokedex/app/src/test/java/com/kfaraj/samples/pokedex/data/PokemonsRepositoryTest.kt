package com.kfaraj.samples.pokedex.data

import androidx.paging.PagingConfig
import androidx.paging.testing.asPagingSourceFactory
import androidx.paging.testing.asSnapshot
import com.kfaraj.samples.pokedex.data.local.PokemonEntity
import com.kfaraj.samples.pokedex.data.local.PokemonsLocalDataSource
import com.kfaraj.samples.pokedex.data.remote.NamedApiResourceList
import com.kfaraj.samples.pokedex.data.remote.PokemonsRemoteDataSource
import com.kfaraj.samples.pokedex.testutils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class PokemonsRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun get() = runTest {
        val pokemonsRemoteDataSource = mockk<PokemonsRemoteDataSource>()
        val pokemonsLocalDataSource = mockk<PokemonsLocalDataSource> {
            coEvery { get(1) } returns BULBASAUR_ENTITY
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
        val response = NamedApiResourceList(1, null, "/", emptyList())
        val pokemonsRemoteDataSource = mockk<PokemonsRemoteDataSource> {
            coEvery { getPokemonSpecies(1, 1) } returns response
        }
        val pagingSourceFactory = listOf(BULBASAUR_ENTITY).asPagingSourceFactory()
        val pagingSource = pagingSourceFactory()
        val pokemonsLocalDataSource = mockk<PokemonsLocalDataSource> {
            coEvery { upsertAll(any()) } returns Unit
            every { getPagingSource() } returns pagingSource
            coEvery { getCount() } returns 1
        }
        val pokemonsRepository = PokemonsRepository(
            pokemonsRemoteDataSource,
            pokemonsLocalDataSource
        )
        val result = pokemonsRepository.getPagingDataStream(PagingConfig(1)).asSnapshot()
        assertEquals(listOf(BULBASAUR), result)
    }

    companion object {
        private val BULBASAUR_ENTITY = PokemonEntity(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
        private val BULBASAUR = Pokemon(
            1,
            "Bulbasaur",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
        )
    }

}

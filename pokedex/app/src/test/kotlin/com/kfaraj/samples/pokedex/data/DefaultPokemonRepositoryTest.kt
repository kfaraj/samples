package com.kfaraj.samples.pokedex.data

import androidx.paging.PagingConfig
import androidx.paging.testing.asPagingSourceFactory
import androidx.paging.testing.asSnapshot
import com.kfaraj.samples.pokedex.data.local.PokemonEntity
import com.kfaraj.samples.pokedex.data.local.PokemonLocalDataSource
import com.kfaraj.samples.pokedex.data.remote.NamedApiResourceList
import com.kfaraj.samples.pokedex.data.remote.PokemonRemoteDataSource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultPokemonRepositoryTest {

    @Test
    fun get() = runTest {
        val pokemonRemoteDataSource = mockk<PokemonRemoteDataSource>()
        val pokemonLocalDataSource = mockk<PokemonLocalDataSource> {
            coEvery { get(1) } returns BULBASAUR_ENTITY
        }
        val pokemonRepository = DefaultPokemonRepository(
            pokemonRemoteDataSource,
            pokemonLocalDataSource
        )
        val result = pokemonRepository.get(1)
        assertEquals(BULBASAUR, result)
    }

    @Test
    fun getPagingDataStream() = runTest {
        val response = NamedApiResourceList(1, null, "/", emptyList())
        val pokemonRemoteDataSource = mockk<PokemonRemoteDataSource> {
            coEvery { getPokemonSpecies(1, 1) } returns response
        }
        val pagingSourceFactory = listOf(BULBASAUR_ENTITY).asPagingSourceFactory()
        val pagingSource = pagingSourceFactory()
        val pokemonLocalDataSource = mockk<PokemonLocalDataSource> {
            coEvery { upsertAll(any()) } returns Unit
            every { getPagingSource() } returns pagingSource
            coEvery { getCount() } returns 1
        }
        val pokemonRepository = DefaultPokemonRepository(
            pokemonRemoteDataSource,
            pokemonLocalDataSource
        )
        val result = pokemonRepository.getPagingDataStream(PagingConfig(1)).asSnapshot()
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

package com.kfaraj.samples.pokedex.data.pokemon

import androidx.paging.PagingSource
import androidx.paging.testing.asPagingSourceFactory
import com.kfaraj.samples.pokedex.data.pokemon.local.PokemonEntity
import com.kfaraj.samples.pokedex.data.pokemon.local.PokemonLocalDataSource
import com.kfaraj.samples.pokedex.data.pokemon.remote.PokemonRemoteDataSource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
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
    fun getPagingSource() = runTest {
        val pokemonRemoteDataSource = mockk<PokemonRemoteDataSource>()
        val pagingSourceFactory = listOf(BULBASAUR_ENTITY).asPagingSourceFactory()
        val pagingSource = pagingSourceFactory()
        val pokemonLocalDataSource = mockk<PokemonLocalDataSource> {
            every { getPagingSource() } returns pagingSource
        }
        val pokemonRepository = DefaultPokemonRepository(
            pokemonRemoteDataSource,
            pokemonLocalDataSource
        )
        val params = PagingSource.LoadParams.Refresh<Int>(null, 1, false)
        val result = pokemonRepository.getPagingSource().load(params)
        assertTrue(result is PagingSource.LoadResult.Page)
        assertEquals(listOf(BULBASAUR), (result as PagingSource.LoadResult.Page).data)
    }

    @Test
    fun getRemoteMediator() = runTest {
        val pokemonRemoteDataSource = mockk<PokemonRemoteDataSource>()
        val pokemonLocalDataSource = mockk<PokemonLocalDataSource>()
        val pokemonRepository = DefaultPokemonRepository(
            pokemonRemoteDataSource,
            pokemonLocalDataSource
        )
        val result = pokemonRepository.getRemoteMediator()
        assertTrue(result is PokemonRemoteMediator)
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

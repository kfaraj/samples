package com.kfaraj.samples.pokedex.shared.data

import androidx.paging.PagingConfig
import androidx.paging.testing.asSnapshot
import com.kfaraj.samples.pokedex.shared.data.local.PokemonEntity
import com.kfaraj.samples.pokedex.shared.data.local.PokemonsLocalDataSource
import com.kfaraj.samples.pokedex.shared.data.remote.NamedApiResourceList
import com.kfaraj.samples.pokedex.shared.data.remote.PokemonsRemoteDataSource
import com.kfaraj.samples.pokedex.shared.testutils.MainDispatcherRule
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

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
        val response = NamedApiResourceList(1, null, "/", emptyList())
        val pokemonsRemoteDataSource = mock<PokemonsRemoteDataSource>().apply {
            whenever(getPokemonSpecies(1, 1)).thenReturn(response)
        }
        val pagingSource = flowOf(listOf(BULBASAUR_ENTITY))
        val pokemonsLocalDataSource = mock<PokemonsLocalDataSource>().apply {
            whenever(getPagingSource(0, 1)).thenReturn(pagingSource)
            whenever(getCount()).thenReturn(1)
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

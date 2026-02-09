package com.kfaraj.samples.pokedex.shared.data.remote

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultPokemonRemoteDataSourceTest {

    @Test
    fun getPokemonSpecies() = runTest {
        val response = NamedApiResourceList(1, null, null, listOf(BULBASAUR_SPECIES_API_RESOURCE))
        val pokeApiService = mockk<PokeApiService> {
            coEvery { getPokemonSpecies(1, 0) } returns response
        }
        val pokemonRemoteDataSource = DefaultPokemonRemoteDataSource(
            pokeApiService
        )
        val result = pokemonRemoteDataSource.getPokemonSpecies(1, 0)
        assertEquals(response, result)
    }

    companion object {
        private val BULBASAUR_SPECIES_API_RESOURCE = NamedApiResource(
            "bulbasaur",
            "https://pokeapi.co/api/v2/pokemon-species/1/"
        )
    }

}

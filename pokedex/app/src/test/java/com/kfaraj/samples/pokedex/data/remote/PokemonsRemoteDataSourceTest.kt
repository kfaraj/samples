package com.kfaraj.samples.pokedex.data.remote

import io.ktor.client.HttpClient
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock

class PokemonsRemoteDataSourceTest {

    @Test
    fun getPokemon() = runTest {
        val response = NamedApiResourceList(1, null, null, listOf(BULBASAUR_API_RESOURCE))
        val pokeApiService = mock<HttpClient>().apply {
            // FIXME: whenever(get("pokemon?limit=1&offset=0")).thenReturn(response)
        }
        val pokemonsRemoteDataSource = PokemonsRemoteDataSource(
            pokeApiService
        )
        val result = pokemonsRemoteDataSource.getPokemon(1, 0)
        assertEquals(response, result)
    }

    companion object {
        private val BULBASAUR_API_RESOURCE = NamedApiResource("bulbasaur", "/1/")
    }

}

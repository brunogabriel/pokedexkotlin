package io.github.brunogabriel.pokedexkotlin.shared.operation

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.brunogabriel.pokedexkotlin.shared.database.PokemonRepository
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.model.PokemonServiceResponse
import io.github.brunogabriel.pokedexkotlin.shared.networking.PokemonService
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import retrofit2.Response

/**
 * Created by brunogabriel on 2019-09-04.
 */
class PokemonOperationsTest {
    private lateinit var pokemonOperations: PokemonOperations
    private lateinit var pokemonRepository: PokemonRepository
    private lateinit var pokemonService: PokemonService

    @Before
    fun setUp() {
        pokemonRepository = mock()
        pokemonService = mock()
        pokemonOperations = PokemonOperations(pokemonRepository, pokemonService)
    }

    @Test
    fun shouldFindPokemonListFromRepository() {
        // given
        val pokemonList = listOf(Pokemon(number = 1L), Pokemon(number = 2L))
        whenever(pokemonRepository.findAll(Pokemon::class.java)).thenReturn(pokemonList)

        // when
        val result = pokemonOperations.findPokemonList()

        // then
        result.test().assertComplete().assertValues(pokemonList).dispose()
    }

    @Test
    fun shouldFindPokemonListFromService() {
        // given
        val pokemonList = listOf(
            Pokemon(url = "http://www.anyurl.com/1/", favorite = false),
            Pokemon(url = "http://www.anyurl.com/2/", favorite = false)
        )
        val expectedList = listOf(
            Pokemon(url = "http://www.anyurl.com/1/", number = 1L, favorite = false),
            Pokemon(url = "http://www.anyurl.com/2/", number = 2L, favorite = false)
        )
        val pokemonResponse = PokemonServiceResponse(results = pokemonList, count = 2)
        whenever(pokemonRepository.findAll(Pokemon::class.java)).thenReturn(emptyList())
        whenever(pokemonService.findPokemons()).thenReturn(Observable.just(Response.success(pokemonResponse)))

        // when
        val result = pokemonOperations.findPokemonList()

        // then
        result.test().assertComplete().assertValues(expectedList).dispose()
    }
}
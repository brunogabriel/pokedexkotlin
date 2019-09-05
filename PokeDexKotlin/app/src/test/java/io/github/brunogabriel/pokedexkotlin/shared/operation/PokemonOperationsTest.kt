package io.github.brunogabriel.pokedexkotlin.shared.operation

import android.accounts.NetworkErrorException
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nhaarman.mockitokotlin2.check
import io.github.brunogabriel.pokedexkotlin.shared.database.PokemonRepository
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.model.PokemonServiceResponse
import io.github.brunogabriel.pokedexkotlin.shared.networking.PokemonService
import io.reactivex.Observable
import junit.framework.TestCase.assertEquals
import okhttp3.ResponseBody
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

    @Test
    fun shouldSavePokemonsFromService() {
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
        pokemonOperations.findPokemonList().test().await().dispose()

        // then
        verify(pokemonRepository).saveAll(expectedList)
    }

    @Test
    fun shouldGetNetworkErrorWhenFindPokemonListFromService() {
        // given
        whenever(pokemonRepository.findAll(Pokemon::class.java)).thenReturn(emptyList())
        whenever(pokemonService.findPokemons())
            .thenReturn(Observable.just(Response.error(404, ResponseBody.create(null, ""))))

        // when
        val result = pokemonOperations.findPokemonList()

        // then
        result.test().assertError(NetworkErrorException::class.java).dispose()
    }

    @Test
    fun shouldFindFavoritePokemons() {
        // given
        val favorites = listOf(mock<Pokemon>())
        whenever(pokemonRepository.findFavorites()).thenReturn(favorites)

        // then
        assertEquals(favorites, pokemonOperations.findFavoritePokemons())
    }

    @Test
    fun shouldSaveOrUpdatePokemon() {
        // given
        val pokemon = mock<Pokemon>()

        // when
        pokemonOperations.saveOrUpdatePokemon(pokemon)

        // then
        verify(pokemonRepository).saveEntity(pokemon)
    }

    @Test
    fun shouldFindPokemon() {
        // given
        val pokemon = mock<Pokemon>()
        whenever(pokemonRepository.findByNumber(1L)).thenReturn(pokemon)

        // then
        assertEquals(pokemon, pokemonOperations.findPokemon(1L))
    }

    @Test
    fun shouldFindPokemonDetails() {
        // given
        val pokemon = mock<Pokemon>()
        whenever(pokemon.hasDetails()).thenReturn(true)
        whenever(pokemonRepository.findByNumber(1L)).thenReturn(pokemon)

        // when
        val result = pokemonOperations.findPokemonDetails(1L)

        // then
        result.test().await().assertComplete().assertValue(pokemon).dispose()
    }

    @Test
    fun shouldFindPokemonDetailsFromService() {
        // given
        val pokemon = Pokemon()
        val pokemonFromService = mock<Pokemon>()
        whenever(pokemonRepository.findByNumber(1L)).thenReturn(pokemon)
        whenever(pokemonFromService.height).thenReturn(1)
        whenever(pokemonFromService.sprites).thenReturn(mock())
        whenever(pokemonService.findPokemonById(1L))
            .thenReturn(Observable.just(Response.success(pokemonFromService)))

        // when
        val result = pokemonOperations.findPokemonDetails(1L)

        // then
        result.test().await().assertValue { it.height == 1 && it.sprites == pokemonFromService.sprites }.dispose()
    }

    @Test
    fun shouldSavePokemonDetailsWhenFindPokemonDetailsFromService() {
        // given
        val pokemon = Pokemon()
        val pokemonFromService = mock<Pokemon>()
        whenever(pokemonRepository.findByNumber(1L)).thenReturn(pokemon)
        whenever(pokemonFromService.height).thenReturn(1)
        whenever(pokemonFromService.sprites).thenReturn(mock())
        whenever(pokemonService.findPokemonById(1L))
            .thenReturn(Observable.just(Response.success(pokemonFromService)))

        // when
        val result = pokemonOperations.findPokemonDetails(1L)

        // then
        result.test().await().dispose()
        verify(pokemonRepository).saveEntity(check {
            assertEquals(it.height, pokemonFromService.height)
            assertEquals(it.sprites, pokemonFromService.sprites)
        })
    }

    @Test
    fun shouldGetNetworkErrorWhenFindPokemonFromService() {
        // given
        val pokemon = Pokemon()
        val pokemonFromService = mock<Pokemon>()
        whenever(pokemonRepository.findByNumber(1L)).thenReturn(pokemon)
        whenever(pokemonFromService.height).thenReturn(1)
        whenever(pokemonFromService.sprites).thenReturn(mock())
        whenever(pokemonService.findPokemonById(1L))
            .thenReturn(Observable.just(Response.error(404, ResponseBody.create(null, ""))))


        // when
        val result = pokemonOperations.findPokemonDetails(1L)

        // then
        result.test().assertError(NetworkErrorException::class.java).dispose()
    }
}
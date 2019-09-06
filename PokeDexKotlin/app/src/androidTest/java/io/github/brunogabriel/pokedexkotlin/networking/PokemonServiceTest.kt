package io.github.brunogabriel.pokedexkotlin.networking

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.model.PokemonServiceResponse
import io.github.brunogabriel.pokedexkotlin.shared.model.PokemonSprites
import io.github.brunogabriel.pokedexkotlin.shared.networking.PokemonService
import io.github.brunogabriel.pokedexkotlin.shared.networking.RetrofitManager
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by brunosantos on 2019-09-05.
 */
@Suppress("MemberVisibilityCanBePrivate")
@RunWith(AndroidJUnit4::class)
class PokemonServiceTest {
    val mockServer = WireMockRule()
        @Rule get

    private lateinit var pokemonService: PokemonService

    @Before
    fun setUp() {
        RetrofitManager.changeBaseUrl("http://127.0.0.1:8080")
        pokemonService = RetrofitManager.createService(PokemonService::class.java)
    }

    @Test
    fun shouldFindPokemonList() {
        // given
        val expected = PokemonServiceResponse(1, "anyNext",
            listOf(Pokemon(name = "charmander")))
        mockServer.stubFor(get(urlPathEqualTo("/pokemon"))
            .withQueryParam("limit", equalTo("251"))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody("{" +
                        "\"count\": 1," +
                        "\"next\": \"anyNext\"," +
                        "\"results\": [" +
                        "   {" +
                        "       \"name\": \"charmander\"" +
                        "   }" +
                        "]" +
                        "}")
            ))

        // then
        pokemonService.findPokemons().test()
            .assertComplete()
            .assertValue { it.isSuccessful && it.body() == expected }
            .dispose()
    }

    @Test
    fun shouldFindPokemonById() {
        // given
        val expected = Pokemon(1L, "bulbasaur", "anyUrl", 100,
            PokemonSprites("back_default", "back_shiny",
                "front_default", "front_shiny")
        )
        mockServer.stubFor(get(urlPathEqualTo("/pokemon/1"))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody("{" +
                        "    \"number\": 1," +
                        "    \"name\": \"bulbasaur\"," +
                        "    \"url\": \"anyUrl\"," +
                        "    \"height\": 100," +
                        "    \"sprites\": {" +
                        "        \"back_default\": \"back_default\"," +
                        "        \"back_shiny\": \"back_shiny\"," +
                        "        \"front_default\": \"front_default\"," +
                        "        \"front_shiny\": \"front_shiny\"" +
                        "    }" +
                        "}")
            ))

        // then
        pokemonService.findPokemonById(1L).test()
            .assertComplete()
            .assertValue { it.isSuccessful && it.body() == expected }
            .dispose()
    }
}
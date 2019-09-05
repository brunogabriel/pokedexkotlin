package io.github.brunogabriel.pokedexkotlin.networking

import androidx.test.runner.AndroidJUnit4
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import io.github.brunogabriel.pokedexkotlin.shared.networking.PokemonService
import io.github.brunogabriel.pokedexkotlin.shared.networking.RetrofitManager
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by brunosantos on 2019-09-05.
 */
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
        mockServer.stubFor(get(urlPathEqualTo("/pokemon"))
            .withQueryParam("limit", equalTo("251"))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody("{" +
                        "    \"count\": 1," +
                        "    \"next\": \"anyNext\"," +
                        "    \"results\": [" +
                        "        {" +
                        "            \"name\": \"charmander\"" +
                        "        }" +
                        "    ]" +
                        "}")
            ))

        // then
        pokemonService.findPokemons().test().assertComplete().assertValue {
            it.isSuccessful && it.body()!!.count == 1 && it.body()!!.results[0].name == "charmander"
        }.dispose()
    }
}
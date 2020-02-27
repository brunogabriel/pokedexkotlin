package io.github.brunogabriel.data_remote.service

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.junit.WireMockRule
import io.github.brunogabriel.data_remote.di.dataRemoteModule
import io.github.brunogabriel.data_remote.di.urlTest
import io.github.brunogabriel.data_remote.models.PokemonPayload
import io.github.brunogabriel.data_remote.models.PokemonResultPayload
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import retrofit2.Retrofit

/**
 * Created by bruno on 27/02/20
 */
@RunWith(
    AndroidJUnit4::class
)
class PokemonListServiceTest : KoinTest {
    @get: Rule
    val mockServer = WireMockRule()

    private lateinit var pokemonListService: PokemonListService
    private lateinit var retrofit: Retrofit

    @Before
    fun setup() {
        startKoin {
            modules(listOf(dataRemoteModule))
        }
        urlTest = "http://127.0.0.1:8080"
        retrofit = get()
        pokemonListService = retrofit.create(PokemonListService::class.java)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun shouldFindPokemons() {
        mockServer.stubFor(
            get(urlPathEqualTo("/pokemon"))
                .withQueryParam("limit", equalTo("251"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody(
                            "{" +
                                    "\"count\": 1," +
                                    "\"results\": [" +
                                    "    {" +
                                    "        \"name\": \"Charmander\"," +
                                    "        \"url\": \"any url\"" +
                                    "    }" +
                                    "]" +
                                    "}"
                        )
                )
        )

        pokemonListService.findPokemons().test()
            .assertComplete()
            .assertValue {
                it == PokemonResultPayload(
                    1,
                    listOf(
                        PokemonPayload(
                            "Charmander",
                            "any url"
                        )
                    )
                )
            }
            .dispose()
    }
}
package io.github.brunogabriel.data_remote.source

import io.github.brunogabriel.data_remote.mapper.toPokemon
import io.github.brunogabriel.data_remote.models.PokemonPayload
import io.github.brunogabriel.data_remote.models.PokemonResultPayload
import io.github.brunogabriel.data_remote.service.PokemonListService
import io.github.brunogabriel.domain.entities.Pokemon
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by bruno on 27/02/20
 */
class PokemonRemoteDataSourceImplementationTest {
    @RelaxedMockK
    private lateinit var service: PokemonListService
    private lateinit var remoteDataSource: PokemonRemoteDataSourceImplementation

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockkStatic("io.github.brunogabriel.data_remote.mapper.PokemonPayloadMapperKt")
        remoteDataSource = PokemonRemoteDataSourceImplementation(service)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should find pokemons`() {
        val pokemonPayloads = listOf(
            PokemonPayload(
                "Mewtwo",
                "http://url.com/v1/150/"
            )
        )
        val expectedPokemons = listOf(
            Pokemon(
                150,
                "Mewtwo",
                "150.png"
            )
        )
        pokemonPayloads.forEachIndexed { index, pokemonPayload ->
            every { pokemonPayload.toPokemon() } returns expectedPokemons[index]
        }
        every { service.findPokemons() } returns Single.just(
            PokemonResultPayload(
                1,
                pokemonPayloads
            )
        )
        remoteDataSource.findPokemons().test()
            .assertValueAt(0, expectedPokemons)
            .assertComplete()
            .dispose()
    }
}

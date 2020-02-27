package io.github.brunogabriel.domain.usecases

import io.github.brunogabriel.domain.entities.Pokemon
import io.github.brunogabriel.domain.repository.PokemonRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

/**
 * Created by bruno on 27/02/20
 */
class FetchPokemonUseCasesTest {
    @MockK(relaxed = true)
    private lateinit var repository: PokemonRepository
    private lateinit var fetchPokemonUseCases: FetchPokemonUseCases

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        fetchPokemonUseCases = FetchPokemonUseCases(repository, Schedulers.trampoline())
    }

    @Test
    fun `should fetch pokemon`() {
        val pokemons = listOf(
            Pokemon("Charmander", "url1"),
            Pokemon("Squirtle", "url2")
        )
        every { repository.fetchPokemons(any()) } returns Single.just(pokemons)

        val expected = fetchPokemonUseCases.fetchPokemons(true)

        expected.test()
            .assertValueAt(0, pokemons)
            .assertComplete()
            .dispose()
    }
}
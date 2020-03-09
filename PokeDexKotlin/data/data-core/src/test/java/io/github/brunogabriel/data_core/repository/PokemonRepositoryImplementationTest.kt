package io.github.brunogabriel.data_core.repository

import io.github.brunogabriel.data_core.source.PokemonCacheDataSource
import io.github.brunogabriel.data_core.source.PokemonRemoteDataSource
import io.github.brunogabriel.domain.entities.Pokemon
import io.github.brunogabriel.domain.repository.PokemonRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by bruno on 08/03/20
 */
class PokemonRepositoryImplementationTest {
    @MockK(relaxed = true)
    private lateinit var cacheDataSource: PokemonCacheDataSource
    @MockK(relaxed = true)
    private lateinit var remoteDataSource: PokemonRemoteDataSource
    private lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        pokemonRepository = PokemonRepositoryImplementation(cacheDataSource, remoteDataSource)
    }

    @Test
    fun `should fetch pokemons from remote service and update cache`() {
        val pokemons = listOf(Pokemon(1, "Bulbasaur", "anyImage"))
        every { remoteDataSource.findPokemons() } returns Single.just(pokemons)

        val expected = pokemonRepository.fetchPokemons(true)

        expected.test()
            .assertValueAt(0, pokemons)
            .assertComplete()
            .dispose()

        verify(exactly = 1) { cacheDataSource.updateData(pokemons) }
        verify(exactly = 0) { cacheDataSource.insertData(any()) }
    }

    @Test
    fun `should find pokemons from cache`() {
        val pokemons = listOf(Pokemon(1, "Bulbasaur", "anyImage"))
        every { cacheDataSource.fetchPhotos() } returns Single.just(pokemons)

        val expected = pokemonRepository.fetchPokemons(false)
        expected.test()
            .assertValueAt(0, pokemons)
            .assertComplete()
            .dispose()

        verify(exactly = 0) { remoteDataSource.findPokemons() }
    }

    @Test
    fun `should insert pokemons on cache when cache is empty and remote got pokemons`() {
        val pokemons = listOf(Pokemon(1, "Bulbasaur", "anyImage"))
        every { cacheDataSource.fetchPhotos() } returns Single.just(emptyList())
        every { remoteDataSource.findPokemons() } returns Single.just(pokemons)

        val expected = pokemonRepository.fetchPokemons(false)

        expected.test()
            .assertValueAt(0, pokemons)
            .assertComplete()
            .dispose()

        verify(exactly = 1) { cacheDataSource.insertData(pokemons) }
        verify(exactly = 0) { cacheDataSource.updateData(any()) }
    }
}

package io.github.brunogabriel.data_local.source

import io.github.brunogabriel.data_local.database.dao.PokemonDao
import io.github.brunogabriel.data_local.models.PokemonCache
import io.github.brunogabriel.domain.entities.Pokemon
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

/**
 * Created by bruno on 28/02/20
 */
class PokemonCacheDataSourceImplementationTest {
    @MockK(relaxed = true)
    private lateinit var dao: PokemonDao
    private lateinit var dataSource: PokemonCacheDataSourceImplementation

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        dataSource = PokemonCacheDataSourceImplementation(dao)
    }

    @Test
    fun `should fetch pokemons`() {
        val pokemons = listOf(
            PokemonCache(1, "Bulbasaur", "url")
        )
        every { dao.findPokemons() } returns Single.just(pokemons)

        val result = dataSource.fetchPokemons()
        result.test()
            .assertValueAt(
                0, listOf(
                    Pokemon(1, "Bulbasaur", "url")
                )
            )
            .assertComplete()
            .dispose()
    }

    @Test
    fun `should insert data`() {
        val pokemons = listOf(Pokemon(1, "Bulbasaur", "url"))
        dataSource.insertData(pokemons)
        verify {
            dao.insertAll(
                listOf(
                    PokemonCache(1, "Bulbasaur", "url")
                )
            )
        }
    }

    @Test
    fun `should update`() {
        val pokemons = listOf(Pokemon(1, "Bulbasaur", "url"))
        dataSource.updateData(pokemons)
        verify {
            dao.update(
                listOf(
                    PokemonCache(1, "Bulbasaur", "url")
                )
            )
        }
    }
}

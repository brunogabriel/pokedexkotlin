package io.github.brunogabriel.pokedexkotlin.shared.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import io.github.brunogabriel.pokedexkotlin.helper.TestApplication
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.realm.Realm
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by brunogabriel on 2019-09-13.
 */
@RunWith(AndroidJUnit4::class)
class PokemonRepositoryTest {

    private lateinit var repository: PokemonRepository

    @Before
    fun setUp() {
        (getInstrumentation().targetContext.applicationContext as TestApplication).createRealmInMemory()
        repository = PokemonRepository()
    }

    @Test
    fun shouldFindPokemonNumber() {
        // given
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                it.insertOrUpdate(
                    listOf(
                        Pokemon(199),
                        Pokemon(251L),
                        Pokemon(359L)
                    )
                )
            }
        }

        // when
        val pokemon = repository.findByNumber(251L)

        // then
        assertNotNull(pokemon)
        assertEquals(251L, pokemon!!.number)
    }

    @Test
    fun shouldFindFavorites() {
        // given
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                it.insertOrUpdate(
                    listOf(
                        Pokemon(199, favorite = true),
                        Pokemon(251L),
                        Pokemon(359L, favorite = true),
                        Pokemon(3579L, favorite = true),
                        Pokemon(511L)
                    )
                )
            }
        }

        // when
        val pokemonList = repository.findFavorites()

        // then
        assertEquals(3, pokemonList.size)
        pokemonList.forEach {
            assertTrue(listOf(199L, 359L, 3579L).contains(it.number!!))
        }
    }
}
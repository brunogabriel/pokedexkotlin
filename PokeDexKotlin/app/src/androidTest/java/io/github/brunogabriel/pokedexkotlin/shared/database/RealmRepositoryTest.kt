package io.github.brunogabriel.pokedexkotlin.shared.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.github.brunogabriel.pokedexkotlin.helper.TestApplication
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.realm.Realm
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by brunogabriel on 2019-09-13.
 */
@RunWith(AndroidJUnit4::class)
class RealmRepositoryTest {

    private lateinit var repository: RealmRepository<Pokemon>

    @Before
    fun setUp() {
        (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApplication).createRealmInMemory()
        repository = PokemonRepository()
    }

    @Test
    fun shouldSaveEntity() {
        // given
        assertEmpty()
        val pokemon = Pokemon(199L)

        // when
        repository.saveEntity(pokemon)

        // then
        val result = Realm.getDefaultInstance().use { realm ->
            realm.where(Pokemon::class.java).equalTo("number", 199L).findFirst()?.let {
                realm.copyFromRealm(it)
            }
        }
        assertEquals(199L, result!!.number)
    }

    @Test
    fun shouldSaveEntities() {
        // given
        assertEmpty()
        val pokemonList = listOf(Pokemon(1L), Pokemon(2L), Pokemon(3L))

        // when
        repository.saveAll(pokemonList)

        // then
        assertEquals(3, Realm.getDefaultInstance().use { it.where(Pokemon::class.java).count() })
    }

    @Test
    fun shouldFindAll() {
        // given
        assertEmpty()
        val pokemonList = listOf(Pokemon(1L), Pokemon(2L), Pokemon(3L), Pokemon(4L), Pokemon(5L))
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                it.insertOrUpdate(pokemonList)
            }
        }

        // then
        assertEquals(5, repository.findAll(Pokemon::class.java).size)
    }

    private fun assertEmpty() {
        assertEquals(0, Realm.getDefaultInstance().use {
            it.where(Pokemon::class.java).count()
        })
    }
}
package io.github.brunogabriel.data_local.databae.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.brunogabriel.data_local.database.AppDatabase
import io.github.brunogabriel.data_local.database.dao.PokemonDao
import io.github.brunogabriel.data_local.models.PokemonCache
import java.io.IOException
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by bruno on 10/03/20
 */
@RunWith(AndroidJUnit4::class)
class PokemonDaoTest {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var pokemonDao: PokemonDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        pokemonDao = appDatabase.pokemonDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun shouldInsertAll() {
        val cache = listOf(
            PokemonCache(1, "Bulbasaur", "url"),
            PokemonCache(2, "Ivysaur", "url")
        )
        pokemonDao.insertAll(cache)
        pokemonDao.findPokemons().test()
            .assertComplete()
            .assertValue { it[0].primaryKey == 1 && it[0].name == "Bulbasaur" }
            .assertValue { it[1].primaryKey == 2 && it[1].name == "Ivysaur" }
            .dispose()
    }

    @Test
    fun shouldUpdateAll() {
        val cache = listOf(
            PokemonCache(1, "Bulbasaur", "url"),
            PokemonCache(2, "Ivysaur", "url")
        )

        val cache2 = listOf(
            PokemonCache(1, "Bulba1", "url"),
            PokemonCache(2, "Ivy1", "url")
        )

        pokemonDao.insertAll(cache)
        pokemonDao.update(cache2)

        pokemonDao.findPokemons().test()
            .assertComplete()
            .assertValue { it[0].primaryKey == 1 && it[0].name == "Bulba1" }
            .assertValue { it[1].primaryKey == 2 && it[1].name == "Ivy1" }
            .dispose()
    }

    @Test
    fun shouldDeleteAll() {
        pokemonDao.insertAll(listOf(
            PokemonCache(1, "Bulbasaur", "url"),
            PokemonCache(2, "Ivysaur", "url")
        ))
        pokemonDao.deleteAll()
        pokemonDao.findPokemons().test()
            .assertComplete()
            .assertValue { it.isEmpty() }
            .dispose()
    }
}

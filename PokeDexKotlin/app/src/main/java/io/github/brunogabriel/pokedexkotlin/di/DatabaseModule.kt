package io.github.brunogabriel.pokedexkotlin.di

import androidx.annotation.VisibleForTesting
import androidx.room.Room
import io.github.brunogabriel.pokedexkotlin.database.PokedexDatabase
import org.koin.dsl.module

/**
 * Created by brunogabriel on 2019-10-10.
 */
@VisibleForTesting
var inMemoryDatabase = false

val databaseModule = module {
    single {
        val database = if (inMemoryDatabase) {
            Room.inMemoryDatabaseBuilder(get(), PokedexDatabase::class.java)
        } else {
            Room.databaseBuilder(get(), PokedexDatabase::class.java, "pokedex.db")
        }
        database.build()
    }
    factory { get<PokedexDatabase>().pokemonDao() }
}
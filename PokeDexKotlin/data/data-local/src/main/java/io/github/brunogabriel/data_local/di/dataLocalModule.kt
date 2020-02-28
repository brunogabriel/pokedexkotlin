package io.github.brunogabriel.data_local.di

import androidx.annotation.VisibleForTesting
import androidx.room.Room
import io.github.brunogabriel.data_core.source.PokemonCacheDataSource
import io.github.brunogabriel.data_local.database.AppDatabase
import io.github.brunogabriel.data_local.source.PokemonCacheDataSourceImplementation
import org.koin.dsl.module

/**
 * Created by bruno on 28/02/20
 */
@VisibleForTesting
var inMemoryDatabase = false

private const val DATABASE_NAME = "pokemon-database.db"

val dataLocalModule = module {
    single {
        if (inMemoryDatabase) {
            Room.inMemoryDatabaseBuilder(get(), AppDatabase::class.java).build()
        } else {
            Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
    factory { get<AppDatabase>().pokemonDao() }
    factory<PokemonCacheDataSource> {
        PokemonCacheDataSourceImplementation(get())
    }
}

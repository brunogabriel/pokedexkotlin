package io.github.brunogabriel.pokedexkotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.brunogabriel.pokedexkotlin.database.dao.PokemonDao
import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon

/**
 * Created by brunogabriel on 2019-10-10.
 */
@Database(
    version = 1,
    exportSchema = false,
    entities = [Pokemon::class]
)
abstract class PokedexDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
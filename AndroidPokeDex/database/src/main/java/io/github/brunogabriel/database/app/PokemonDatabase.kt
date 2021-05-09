package io.github.brunogabriel.database.app

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.brunogabriel.database.app.dao.PokemonDao
import io.github.brunogabriel.database.models.PokemonCache

@Database(
    version = 1,
    exportSchema = false,
    entities = [PokemonCache::class]
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
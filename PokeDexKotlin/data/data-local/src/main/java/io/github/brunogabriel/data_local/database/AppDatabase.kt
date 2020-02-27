package io.github.brunogabriel.data_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.brunogabriel.data_local.models.PokemonCache

/**
 * Created by bruno on 27/02/20
 */
@Database(
    version = 1,
    exportSchema = false,
    entities = [PokemonCache::class]
)
abstract class AppDatabase : RoomDatabase() {
}
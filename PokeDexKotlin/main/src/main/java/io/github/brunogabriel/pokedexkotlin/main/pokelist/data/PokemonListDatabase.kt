package io.github.brunogabriel.pokedexkotlin.main.pokelist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1
)
abstract class PokemonListDatabase : RoomDatabase() {
    abstract fun pokemonListDao(): PokemonListDao
}
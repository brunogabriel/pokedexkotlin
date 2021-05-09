package io.github.brunogabriel.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "pokemons"
)
data class PokemonCache(
    @PrimaryKey
    val primaryKey: Int,
    val name: String,
    val url: String
)
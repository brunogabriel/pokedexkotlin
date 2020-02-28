package io.github.brunogabriel.data_local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by bruno on 27/02/20
 */
@Entity(tableName = "pokemons")
data class PokemonCache(
    @PrimaryKey
    val primaryKey: Int,
    val name: String,
    val url: String
)

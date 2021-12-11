package io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PokemonEntity(
    @PrimaryKey
    val number: String,
    val name: String,
    val url: String
)
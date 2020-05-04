package io.github.brunogabriel.data_local.mapper

import io.github.brunogabriel.data_local.models.PokemonCache
import io.github.brunogabriel.domain.entities.Pokemon

/**
 * Created by bruno on 27/02/20
 */
fun PokemonCache.toPokemon() = Pokemon(
    number = this.primaryKey,
    name = this.name,
    imageUrl = this.url
)

fun Pokemon.toPokemonCache() = PokemonCache(
    primaryKey = this.number,
    name = this.name,
    url = this.imageUrl
)

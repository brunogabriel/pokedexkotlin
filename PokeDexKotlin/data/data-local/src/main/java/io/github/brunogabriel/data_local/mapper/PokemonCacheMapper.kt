package io.github.brunogabriel.data_local.mapper

import io.github.brunogabriel.data_local.models.PokemonCache
import io.github.brunogabriel.domain.entities.Pokemon

/**
 * Created by bruno on 27/02/20
 */
class PokemonCacheMapper {
    fun mapToPokemon(data: List<PokemonCache>) = data.map {
        Pokemon(
            number = it.primaryKey,
            name = it.name,
            imageUrl = it.url
        )
    }

    fun mapToCache(data: List<Pokemon>) = data.map {
        PokemonCache(
            primaryKey = it.number,
            name = it.name,
            url = it.imageUrl
        )
    }
}
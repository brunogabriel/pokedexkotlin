package io.github.brunogabriel.pokedexkotlin.main.pokelist.domain

import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.entity.PokemonEntity
import io.github.brunogabriel.pokedexkotlin.main.pokelist.domain.models.PokemonVO
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.response.PokemonResponse

fun entityToViewObject(
    entity: PokemonEntity
): PokemonVO {
    return PokemonVO(
        name = entity.name,
        number = entity.number
    )
}

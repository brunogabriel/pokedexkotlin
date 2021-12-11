package io.github.brunogabriel.pokedexkotlin.main.pokelist.data

import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.response.PokemonListResponse
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.service.PokemonListService
import javax.inject.Inject

internal class PokeListRepositoryImpl @Inject constructor(
    private val service: PokemonListService
) : PokeListRepository {
    override suspend fun getPokemons(
        limit: Int,
        offset: Int,
        forceRefresh: Boolean
    ): PokemonListResponse {
        return service.getPokemons(limit, offset)
    }
}

interface PokeListRepository {
    suspend fun getPokemons(
        limit: Int,
        offset: Int,
        forceRefresh: Boolean
    ): PokemonListResponse
}
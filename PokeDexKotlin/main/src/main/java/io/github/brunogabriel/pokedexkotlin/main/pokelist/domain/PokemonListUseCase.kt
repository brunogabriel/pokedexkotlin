package io.github.brunogabriel.pokedexkotlin.main.pokelist.domain

import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.PokeListRepository
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.Pokemon
import javax.inject.Inject

internal class PokemonListUseCaseImpl @Inject constructor(
    private val repository: PokeListRepository
) : PokemonListUseCase {
    override suspend fun fetchAll(limit: Int, offset: Int, forceRefresh: Boolean) =
        repository.getPokemons(limit, offset, forceRefresh)
            .results.map(::pokemonResponseToPokemon)
}

interface PokemonListUseCase {
    suspend fun fetchAll(
        limit: Int,
        offset: Int,
        forceRefresh: Boolean
    ): List<Pokemon>
}
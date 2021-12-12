package io.github.brunogabriel.pokedexkotlin.main.pokelist.domain

import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.PokemonListRepository
import io.github.brunogabriel.pokedexkotlin.main.pokelist.domain.models.PokemonVO
import javax.inject.Inject

internal class PokemonListUseCaseImpl @Inject constructor(
    private val repository: PokemonListRepository
) : PokemonListUseCase {
    override suspend fun fetchAll(limit: Int, offset: Int, forceRefresh: Boolean) =
        repository.getPokemons(limit, offset, forceRefresh)
            .map(::entityToViewObject)
}

interface PokemonListUseCase {
    suspend fun fetchAll(
        limit: Int,
        offset: Int,
        forceRefresh: Boolean
    ): List<PokemonVO>
}
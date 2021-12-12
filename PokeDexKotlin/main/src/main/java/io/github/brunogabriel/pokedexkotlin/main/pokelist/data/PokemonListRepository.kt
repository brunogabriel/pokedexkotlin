package io.github.brunogabriel.pokedexkotlin.main.pokelist.data

import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.mapper.responseToEntity
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.entity.PokemonEntity
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.service.PokemonListService
import javax.inject.Inject

internal class PokemonListRepositoryImpl @Inject constructor(
    private val service: PokemonListService,
    private val dao: PokemonListDao
) : PokemonListRepository {
    override suspend fun getPokemons(
        limit: Int,
        offset: Int,
        forceRefresh: Boolean
    ): List<PokemonEntity> {
        return if (forceRefresh) {
            fetchFromService(limit, offset)
        } else {
            val fromDatabase = dao.getEntities(limit, offset)
            if (fromDatabase.isEmpty()) {
                fetchFromService(limit, offset)
            } else {
                fromDatabase
            }
        }
    }

    private suspend fun fetchFromService(limit: Int, offset: Int): List<PokemonEntity> {
        val entities = service.getPokemons(limit, offset).results.map(::responseToEntity)
        dao.insertAll(entities)
        return entities
    }
}

interface PokemonListRepository {
    suspend fun getPokemons(
        limit: Int,
        offset: Int,
        forceRefresh: Boolean
    ): List<PokemonEntity>
}
package io.github.brunogabriel.pokedexkotlin.main.pokelist.data.service

import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.response.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonListService {
    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponse
}
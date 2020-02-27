package io.github.brunogabriel.data_remote.service

import io.github.brunogabriel.data_remote.models.PokemonPayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by bruno on 27/02/20
 */
interface PokemonListService {
    @GET("pokemon")
    fun findPokemons(@Query("limit") limit: Int = 251): Single<List<PokemonPayload>>
}
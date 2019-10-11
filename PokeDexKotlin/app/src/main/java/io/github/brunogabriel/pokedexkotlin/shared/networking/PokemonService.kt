package io.github.brunogabriel.pokedexkotlin.shared.networking

import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by brunogabriel on 2019-10-10.
 */
interface PokemonService {
    @GET("pokemon")
    fun fetchPokemons(@Query("limit") limit: Int = 251): Single<PokemonServiceResponse>

    @GET("pokemon/{id}")
    fun fetchDetailsById(@Path("id") id: Long): Single<Pokemon>
}
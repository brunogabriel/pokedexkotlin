package io.github.brunogabriel.pokedexkotlin.shared.networking

import io.github.brunogabriel.pokedexkotlin.shared.model.PokemonServiceResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by brunogabriel on 2019-08-31.
 */
interface PokemonService {
    @GET("pokemon/")
    fun findPokemons(@Query("limit") limit: Int = 251): Observable<Response<PokemonServiceResponse>>
}
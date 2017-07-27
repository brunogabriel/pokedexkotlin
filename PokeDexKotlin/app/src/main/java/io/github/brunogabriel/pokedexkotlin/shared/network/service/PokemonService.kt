package io.github.brunogabriel.pokedexkotlin.shared.network.service

import io.github.brunogabriel.pokedexkotlin.shared.models.ResourceSummaryList
import retrofit2.http.GET
import rx.Observable

/**
 * Created by bruno on 7/26/17.
 */
interface PokemonService {
    @GET("api/v2/pokemon/?limit=251")
    fun getPokemonResourceSummary(): Observable<ResourceSummaryList>
}
package io.github.brunogabriel.data_remote.models

import com.google.gson.annotations.SerializedName

/**
 * Created by bruno on 27/02/20
 */
data class PokemonResultPayload(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<PokemonPayload>
)
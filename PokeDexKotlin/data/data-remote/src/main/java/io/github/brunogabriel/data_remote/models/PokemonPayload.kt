package io.github.brunogabriel.data_remote.models

import com.google.gson.annotations.SerializedName

/**
 * Created by bruno on 27/02/20
 */
data class PokemonPayload(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
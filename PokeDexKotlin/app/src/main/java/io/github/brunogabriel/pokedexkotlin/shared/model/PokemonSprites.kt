package io.github.brunogabriel.pokedexkotlin.shared.model

import com.squareup.moshi.Json
import io.realm.RealmModel
import io.realm.annotations.RealmClass

/**
 * Created by brunogabriel on 2019-09-03.
 */
@RealmClass
open class PokemonSprites(
    @Json(name = "back_default") var backDefault: String? = null,
    @Json(name = "back_shiny") var backShiny: String? = null,
    @Json(name = "front_default") var frontDefault: String? = null,
    @Json(name = "front_shiny") var frontShiny: String? = null
) : RealmModel
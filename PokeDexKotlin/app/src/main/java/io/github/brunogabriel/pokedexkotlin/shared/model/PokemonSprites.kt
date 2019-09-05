package io.github.brunogabriel.pokedexkotlin.shared.model

import com.squareup.moshi.Json
import io.realm.RealmModel
import io.realm.annotations.RealmClass

/**
 * Created by brunogabriel on 2019-09-03.
 */
@RealmClass
open class PokemonSprites(
    @field:Json(name = "back_default") var backDefault: String? = null,
    @field:Json(name = "back_shiny") var backShiny: String? = null,
    @field:Json(name = "front_default") var frontDefault: String? = null,
    @field:Json(name = "front_shiny") var frontShiny: String? = null
) : RealmModel
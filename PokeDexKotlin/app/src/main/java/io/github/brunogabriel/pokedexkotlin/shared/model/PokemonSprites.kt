package io.github.brunogabriel.pokedexkotlin.shared.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.annotations.RealmClass

/**
 * Created by brunogabriel on 2019-09-03.
 */
@RealmClass
open class PokemonSprites(
    @field:SerializedName("back_default") var backDefault: String? = null,
    @field:SerializedName("back_shiny") var backShiny: String? = null,
    @field:SerializedName("front_default") var frontDefault: String? = null,
    @field:SerializedName("front_shiny") var frontShiny: String? = null
) : RealmModel {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PokemonSprites

        if (backDefault != other.backDefault) return false
        if (backShiny != other.backShiny) return false
        if (frontDefault != other.frontDefault) return false
        if (frontShiny != other.frontShiny) return false

        return true
    }

    override fun hashCode(): Int {
        var result = backDefault?.hashCode() ?: 0
        result = 31 * result + (backShiny?.hashCode() ?: 0)
        result = 31 * result + (frontDefault?.hashCode() ?: 0)
        result = 31 * result + (frontShiny?.hashCode() ?: 0)
        return result
    }
}
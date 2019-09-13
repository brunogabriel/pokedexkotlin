package io.github.brunogabriel.pokedexkotlin.shared.model

import io.realm.RealmModel
import io.realm.annotations.RealmClass

/**
 * Created by brunogabriel on 2019-09-07.
 */
@RealmClass
open class PokemonType(
    var slot: Int? = null,
    var type: Type? = null
) : RealmModel {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PokemonType

        if (slot != other.slot) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = slot ?: 0
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }
}
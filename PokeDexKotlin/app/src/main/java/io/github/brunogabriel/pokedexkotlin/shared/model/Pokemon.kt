package io.github.brunogabriel.pokedexkotlin.shared.model

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by brunogabriel on 2019-08-29.
 */
@RealmClass
open class Pokemon(
    @PrimaryKey
    var number: Long? = null,
    var name: String? = null,
    var url: String? = null,
    var height: Int? = null,
    var weight: Int? = null,
    var sprites: PokemonSprites? = null,
    var types: RealmList<PokemonType> = RealmList(),
    var favorite: Boolean = false,
    var details: Boolean = false
) : RealmModel {
    companion object {
        const val SPRITE_BASE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    }

    fun findSpriteUrl() = "$SPRITE_BASE_URL$number.png"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Pokemon

        if (number != other.number) return false
        if (name != other.name) return false
        if (url != other.url) return false
        if (height != other.height) return false
        if (weight != other.weight) return false
        if (sprites != other.sprites) return false
        if (types != other.types) return false
        if (favorite != other.favorite) return false
        return true
    }

    override fun hashCode(): Int {
        var result = number?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (height ?: 0)
        result = 31 * result + (weight ?: 0)
        result = 31 * result + (sprites?.hashCode() ?: 0)
        result = 31 * result + types.hashCode()
        result = 31 * result + favorite.hashCode()
        return result
    }
}
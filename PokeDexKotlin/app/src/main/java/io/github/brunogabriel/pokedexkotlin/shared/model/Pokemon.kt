package io.github.brunogabriel.pokedexkotlin.shared.model

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
    var sprites: PokemonSprites? = null,
    var favorite: Boolean = false
) : RealmModel {
    companion object {
        private const val SPRITE_BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    }
    fun findSpriteUrl() = "$SPRITE_BASE_URL$number.png"
}
package io.github.brunogabriel.pokedexkotlin.shared.model

import io.github.brunogabriel.pokedexkotlin.R
import io.realm.RealmModel
import io.realm.annotations.RealmClass

/**
 * Created by brunogabriel on 2019-09-07.
 */
@RealmClass
open class Type(
    var name: String? = null
) : RealmModel {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    fun findResource(): Int? {
        return when (name) {
            "bug" -> R.drawable.bug
            "dark" -> R.drawable.dark
            "dragon" -> R.drawable.dragon
            "electric" -> R.drawable.electric
            "fairy" -> R.drawable.fairy
            "fighting" -> R.drawable.fight
            "fire" -> R.drawable.fire
            "flying" -> R.drawable.flying
            "ghost" -> R.drawable.ghost
            "grass" -> R.drawable.grass
            "ground" -> R.drawable.ground
            "ice" -> R.drawable.ic_close
            "normal" -> R.drawable.normal
            "poison" -> R.drawable.poison
            "psychic" -> R.drawable.psychic
            "rock" -> R.drawable.rock
            "steel" -> R.drawable.steel
            "water" -> R.drawable.water
            else -> null
        }
    }
}
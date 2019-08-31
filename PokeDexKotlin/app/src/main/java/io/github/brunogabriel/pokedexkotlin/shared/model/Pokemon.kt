package io.github.brunogabriel.pokedexkotlin.shared.model

import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by brunogabriel on 2019-08-29.
 */
//data class Pokemon(val name: String, val url: String) {
//    companion object {
//        val regex = """.*[//](\d+)[//]""".toRegex()
//        private const val SPRITE_BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
//    }
//    fun findNumber() = regex.find(url)?.destructured?.component1()?.toInt()?.plus(1)
//    fun findSpriteUrl(number: Int? = findNumber()) = "$SPRITE_BASE_URL$number.png"
//}


@RealmClass
open class Pokemon(
    @PrimaryKey
    var number: Long? = null,
    var name: String? = null,
    var url: String? = null,
    var favorite: Boolean = false
) : RealmModel {
    companion object {
        private const val SPRITE_BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    }
    fun findSpriteUrl() = "$SPRITE_BASE_URL$number.png"
}
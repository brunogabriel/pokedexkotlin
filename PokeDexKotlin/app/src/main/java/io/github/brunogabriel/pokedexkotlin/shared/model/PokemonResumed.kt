package io.github.brunogabriel.pokedexkotlin.shared.model

/**
 * Created by brunogabriel on 2019-08-29.
 */
data class PokemonResumed(val name: String, val url: String) {
    companion object {
        val regex = """.*[//](\d+)[//]""".toRegex()
        private const val SPRITE_BASE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    }
    fun findNumber() = regex.find(url)?.destructured?.component1()?.toInt()?.plus(1)
    fun findSpriteUrl(number: Int? = findNumber()) = "$SPRITE_BASE_URL$number.png"
}
package io.github.brunogabriel.pokedexkotlin.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by brunogabriel on 2019-10-10.
 */
@Entity(tableName = "pokemons")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var number: Long? = 0L,
    var name: String = "",
    var url: String? = null,
    var height: Int? = null,
    var favorite: Boolean = false,
    @Embedded
    var sprites: PokemonSprites? = null
) {
    companion object {
        const val SPRITE_BASE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
    }

    fun findSpriteUrl() = "$SPRITE_BASE_URL$number.png"
}

data class PokemonSprites(
    @field:SerializedName("back_default")
    var backDefault: String? = null,
    @field:SerializedName("back_shiny")
    var backShiny: String? = null,
    @field:SerializedName("front_default")
    var frontDefault: String? = null,
    @field:SerializedName("front_shiny")
    var frontShiny: String? = null
)

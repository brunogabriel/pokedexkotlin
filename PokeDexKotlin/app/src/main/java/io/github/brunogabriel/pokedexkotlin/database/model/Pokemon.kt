package io.github.brunogabriel.pokedexkotlin.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

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
    var details: Boolean = false
    // TODO: add details and sprites
)
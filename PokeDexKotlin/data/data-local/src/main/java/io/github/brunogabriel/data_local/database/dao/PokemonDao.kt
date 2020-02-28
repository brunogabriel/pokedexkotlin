package io.github.brunogabriel.data_local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.github.brunogabriel.data_local.models.PokemonCache
import io.reactivex.Single

/**
 * Created by bruno on 27/02/20
 */
@Dao
interface PokemonDao {
    @Insert
    fun insertAll(photos: List<PokemonCache>)

    @Query("SELECT * FROM pokemons")
    fun findPhotos(): Single<List<PokemonCache>>

    @Transaction
    fun update(photos: List<PokemonCache>) {
        deleteAll()
        insertAll(photos)
    }

    @Query("DELETE FROM pokemons")
    fun deleteAll()
}

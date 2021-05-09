package io.github.brunogabriel.database.app.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.github.brunogabriel.database.models.PokemonCache
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface PokemonDao {
    @Insert
    fun insertAll(pokemons: List<PokemonCache>): Completable

    @Query("SELECT * FROM pokemons")
    fun findAll(): Maybe<List<PokemonCache>>

    @Transaction
    fun update(pokemons: List<PokemonCache>) {
        deleteAll()
        insertAll(pokemons)
    }

    @Query("DELETE FROM pokemons")
    fun deleteAll(): Completable
}
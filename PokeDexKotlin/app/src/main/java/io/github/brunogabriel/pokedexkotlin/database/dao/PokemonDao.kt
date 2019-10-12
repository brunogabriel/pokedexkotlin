package io.github.brunogabriel.pokedexkotlin.database.dao

import androidx.room.*
import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by brunogabriel on 2019-10-10.
 */
@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemons")
    fun findAll(): Flowable<List<Pokemon>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(pokemonList: List<Pokemon>): Completable

    @Update
    fun updatePokemon(pokemon: Pokemon): Completable
}
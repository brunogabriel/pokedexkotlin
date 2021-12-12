package io.github.brunogabriel.pokedexkotlin.main.pokelist.data

import androidx.room.*
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.models.entity.PokemonEntity

@Dao
interface PokemonListDao {
    @Query("SELECT * FROM pokemonentity LIMIT :limit OFFSET :offset")
    suspend fun getEntities(
        limit: Int,
        offset: Int
    ): List<PokemonEntity>

    @Insert
    fun insertAll(entities: List<PokemonEntity>)

    @Delete
    fun delete(entity: PokemonEntity)

    @Update
    fun update(entity: PokemonEntity)
}
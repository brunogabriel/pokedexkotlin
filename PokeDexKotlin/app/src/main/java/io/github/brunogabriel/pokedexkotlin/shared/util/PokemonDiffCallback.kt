package io.github.brunogabriel.pokedexkotlin.shared.util

import androidx.recyclerview.widget.DiffUtil
import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon

/**
 * Created by brunogabriel on 2019-10-10.
 */
class PokemonDiffCallback(
    private val oldPokemons: List<Pokemon>,
    private val newPokemons: List<Pokemon>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPokemons[oldItemPosition] == newPokemons[newItemPosition]
    }

    override fun getOldListSize() = oldPokemons.size

    override fun getNewListSize() = newPokemons.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPokemons[oldItemPosition] == newPokemons[newItemPosition]
    }
}
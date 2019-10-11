package io.github.brunogabriel.pokedexkotlin.shared.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.extensions.inflate
import io.github.brunogabriel.pokedexkotlin.shared.util.PokemonDiffCallback
import kotlinx.android.synthetic.main.holder_pokemon_card.view.*

/**
 * Created by brunogabriel on 2019-10-10.
 */
class PokemonGridAdapter() : RecyclerView.Adapter<PokemonGridAdapter.PokemonViewHolder>() {
    private var pokemonList = mutableListOf<Pokemon>()

    fun setPokemons(newPokemons: List<Pokemon>) {
        val diffCallback = PokemonDiffCallback(pokemonList, newPokemons)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        pokemonList.clear()
        pokemonList.addAll(newPokemons)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(parent.inflate(R.layout.holder_pokemon_card))
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) = with(itemView) {
            pokemon_name_text.text = pokemon.name.capitalize()
        }
    }
}
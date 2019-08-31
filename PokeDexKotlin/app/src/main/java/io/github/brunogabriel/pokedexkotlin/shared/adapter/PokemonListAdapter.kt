package io.github.brunogabriel.pokedexkotlin.shared.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.shared.extensions.inflate
import io.github.brunogabriel.pokedexkotlin.shared.extensions.loadImage
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import kotlinx.android.synthetic.main.holder_pokemon.view.*

/**
 * Created by brunogabriel on 2019-08-29.
 */
class PokemonListAdapter : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>(), RecyclerViewAdapterContract<Pokemon> {
    var pokemons: MutableList<Pokemon> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PokemonViewHolder(parent.inflate(R.layout.holder_pokemon))

    override fun getItemCount() = pokemons.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    override fun insertItems(items: List<Pokemon>) {
        if (items.isNotEmpty()) {
            val sizeBeforeInsertNewItems = pokemons.size
            pokemons.addAll(items)
            notifyItemRangeInserted(sizeBeforeInsertNewItems, items.size)
        }
    }

    override fun updateItemAtPosition(newItem: Pokemon, position: Int) {
        if (pokemons.size > position) {
            pokemons[position] = newItem
            notifyItemChanged(position)
        }
    }

    override fun removeItemAtPosition(position: Int) {
        if (pokemons.size > position) {
            pokemons.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) = with(itemView) {
            pokemon_name_text.text = pokemon.name?.capitalize()
            pokemon_number_text.text = "${pokemon.number ?: "??"}"
            pokemon_image.loadImage(pokemon.findSpriteUrl()) // TODO: make image caching
        }
    }
}
package io.github.brunogabriel.pokedexkotlin.shared.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.shared.extensions.inflate
import io.github.brunogabriel.pokedexkotlin.shared.extensions.loadImage
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import kotlinx.android.synthetic.main.holder_pokemon_list.view.*

/**
 * Created by brunogabriel on 2019-09-07.
 */
class PokemonListAdapter(private val pokemonList: List<Pokemon>,
                         private val onClickAction: (pokemon: Pokemon, position: Int) -> Unit ) :
    RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(parent.inflate(R.layout.holder_pokemon_list))
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemon: Pokemon) = with(itemView) {
            content_holder.setOnClickListener {
                onClickAction(pokemonList[adapterPosition], adapterPosition)
            }
            pokemon_name_text.text = pokemon.name?.capitalize()
            pokemon_number_text.text = pokemon.number?.toString()
            pokemon_image.loadImage(pokemon.findSpriteUrl())
        }
    }
}
package io.github.brunogabriel.pokedexkotlin.shared.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.extensions.inflate
import io.github.brunogabriel.pokedexkotlin.shared.extensions.loadImage
import kotlinx.android.synthetic.main.holder_pokemon_card.view.*

/**
 * Created by brunogabriel on 2019-10-10.
 */
class PokemonGridAdapter(
    private val favoriteClickAction: (Pokemon) -> Unit
) : RecyclerView.Adapter<PokemonGridAdapter.PokemonViewHolder>() {
    private var pokemonList = mutableListOf<Pokemon>()

    fun setPokemons(newPokemons: List<Pokemon>) {
        pokemonList.clear()
        pokemonList.addAll(newPokemons)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(parent.inflate(R.layout.holder_pokemon_card))
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("DefaultLocale")
        fun bind(pokemon: Pokemon) = with(itemView) {
            pokemon_name_text.text = pokemon.name.capitalize()
            pokemon_image.loadImage(pokemon.findSpriteUrl())
            pokemon_number_text.text = pokemon.number.toString()
            pokemon_favorite_image.setOnClickListener {
                favoriteClickAction(
                    pokemon
                )
            }
            pokemon_favorite_image.setImageResource(
                if (pokemon.favorite) {
                    R.drawable.ic_favorite
                } else {
                    R.drawable.ic_favorite_border
                }
            )
        }
    }
}
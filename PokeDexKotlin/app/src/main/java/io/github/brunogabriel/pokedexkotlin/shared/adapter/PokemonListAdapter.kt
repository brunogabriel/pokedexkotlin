package io.github.brunogabriel.pokedexkotlin.shared.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.shared.extensions.inflate
import io.github.brunogabriel.pokedexkotlin.shared.extensions.loadImage
import io.github.brunogabriel.pokedexkotlin.shared.model.PokemonResumed
import kotlinx.android.synthetic.main.holder_pokemon.view.*

/**
 * Created by brunogabriel on 2019-08-29.
 */
class PokemonListAdapter(private val pokemons: List<PokemonResumed>) : RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PokemonViewHolder(parent.inflate(R.layout.holder_pokemon))

    override fun getItemCount() = pokemons.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(pokemons[position])
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(pokemonResumed: PokemonResumed) = with(itemView) {
            val pokemonNumber = pokemonResumed.findNumber()
            pokemon_name_text.text = pokemonResumed.name
            pokemon_number_text.text = "#$pokemonNumber"
            pokemon_image.loadImage(pokemonResumed.findSpriteUrl(pokemonNumber))
        }
    }
}
package io.github.brunogabriel.pokemon_list.presentation.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.core_app.extensions.inflate
import io.github.brunogabriel.core_app.extensions.loadImage
import io.github.brunogabriel.domain.entities.Pokemon
import io.github.brunogabriel.pokemon_list.R
import kotlinx.android.synthetic.main.holder_pokemon.view.*

/**
 * Created by bruno on 28/02/20
 */
class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    var pokemonList: List<Pokemon> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.holder_pokemon))
    }

    override fun getItemCount() = pokemonList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pokemonList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("DefaultLocale")
        fun bind(pokemon: Pokemon) = with(itemView) {
            pokemon_name_text_view.text = pokemon.name.capitalize()
            pokemon_number_text_view.text = context.getString(R.string.pokemon_number_pattern, pokemon.number)
            pokemon_image_view.loadImage(pokemon.imageUrl)
        }
    }
}

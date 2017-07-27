package io.github.brunogabriel.pokedexkotlin.main.list

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.shared.models.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.ui.bind
import io.github.brunogabriel.pokedexkotlin.shared.ui.inflate
import io.github.brunogabriel.pokedexkotlin.shared.ui.load

/**
 * Created by bruno on 7/26/17.
 */
class PokemonNamedAdapter(private val pokemons: List<Pokemon>): RecyclerView.Adapter<PokemonNamedAdapter.PokemonNamedViewHolder>() {

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PokemonNamedViewHolder {
        return PokemonNamedViewHolder(parent?.inflate(R.layout.holder_pokemon)!!)
    }

    override fun onBindViewHolder(holder: PokemonNamedViewHolder, position: Int) {
        holder.name.text = "#${position+1}\n${pokemons[position].name.capitalize()}"
        holder.image.load(imageUrl(position + 1))
    }

    class PokemonNamedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name by bind<AppCompatTextView>(R.id.name)
        val image by bind<ImageView>(R.id.image)
    }

    private fun imageUrl(pokemonIndex: Int) : String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonIndex}.png"
    }
}
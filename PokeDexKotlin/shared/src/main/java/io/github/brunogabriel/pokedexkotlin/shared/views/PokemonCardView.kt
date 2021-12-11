package io.github.brunogabriel.pokedexkotlin.shared.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import io.github.brunogabriel.pokedexkotlin.shared.databinding.PokemonCardViewBinding
import io.github.brunogabriel.pokedexkotlin.shared.extensions.loadImage

class PokemonCardView(
    context: Context,
    attributeSet: AttributeSet? = null
) : MaterialCardView(context, attributeSet) {

    private val binding: PokemonCardViewBinding =
        PokemonCardViewBinding
            .inflate(LayoutInflater.from(context), this, true)

    fun bind(name: String,
             number: String,
             sprite: String) {
        with(binding) {
            pokemonName.text = name
            pokemonNumber.text = number
            pokemonImage.loadImage(sprite, cached = true)
        }
    }
}
package io.github.brunogabriel.pokedexkotlin.main.pokelist.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.pokedexkotlin.main.pokelist.domain.models.PokemonVO
import io.github.brunogabriel.pokedexkotlin.shared.views.PokemonCardView

class PokemonListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Any>()

    fun add(newItems: List<PokemonVO>) {
        // TODO: Refactor in future
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // TODO: use types
        return PokemonViewHolder(
            PokemonCardView(parent.context)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PokemonViewHolder).bind(items[position] as PokemonVO)
//        when (holder) {
//            is PokemonViewHolder -> holder.bind(items[position] as Pokemon)
//        }
    }

    override fun getItemCount() = items.size

    internal class PokemonViewHolder(private val cardView: PokemonCardView) :
        RecyclerView.ViewHolder(cardView) {

        fun bind(pokemonVO: PokemonVO) {
            cardView.bind(
                pokemonVO.name.replaceFirstChar(Char::titlecase),
                pokemonVO.getFormattedNumber(),
                pokemonVO.getSprite()
            )
        }
    }

    companion object {
        private const val VIEW_TYPE_POKEMON = 1
        private const val VIEW_TYPE_LOADING = 2
        private const val VIEW_TYPE_TRY_AGAIN = 3
    }
}
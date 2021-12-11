package io.github.brunogabriel.pokedexkotlin.main.pokelist.presentation.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.github.brunogabriel.pokedexkotlin.shared.extensions.px

class PokemonListDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: parent.childCount

        with(outRect) {
            top = 16.px
            if (position % 2 == 0) {
                left = 16.px
                right = 4.px
            } else {
                left = 4.px
                right = 16.px
            }

            if (itemCount > 0 && position >= itemCount - 2) {
                bottom = 16.px
            }
        }
    }
}
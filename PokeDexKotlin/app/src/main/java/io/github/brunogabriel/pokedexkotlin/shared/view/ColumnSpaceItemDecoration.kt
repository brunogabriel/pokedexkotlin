package io.github.brunogabriel.pokedexkotlin.shared.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by brunogabriel on 2019-08-29.
 */
class ColumnSpaceItemDecoration(
    private val space: Int,
    private val spanCount: Int
) : RecyclerView.ItemDecoration() {
    private val spaceBySpan = space / spanCount
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        with(outRect) {
            left = space - (column * space) / spaceBySpan
            right = (column + 1) * spaceBySpan
            if (position < spanCount) { // first line
                top = space
            }
            bottom = space
        }
    }
}
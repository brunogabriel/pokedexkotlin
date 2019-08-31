package io.github.brunogabriel.pokedexkotlin.shared.adapter

/**
 * Created by brunogabriel on 2019-08-31.
 */
interface RecyclerViewAdapterContract<T> {
    fun insertItems(items: List<T>)
    fun updateItemAtPosition(newItem: T, position: Int)
    fun removeItemAtPosition(position: Int)
}
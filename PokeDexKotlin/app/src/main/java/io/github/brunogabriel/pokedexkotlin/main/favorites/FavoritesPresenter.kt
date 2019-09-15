package io.github.brunogabriel.pokedexkotlin.main.favorites

import io.github.brunogabriel.pokedexkotlin.shared.operation.PokemonOperations

/**
 * Created by brunogabriel on 2019-09-07.
 */
class FavoritesPresenter(
    private val view: FavoritesContract.View,
    private val pokemonOperations: PokemonOperations = PokemonOperations()
) : FavoritesContract.Presenter {
    override fun onViewWillAppear() {
        pokemonOperations.findFavoritePokemons().let {
            if (it.isEmpty()) {
                view.showEmptyFavorites()
            } else {
                view.showFavorites(it)
            }
        }
    }
}
package io.github.brunogabriel.pokedexkotlin.main.favorites

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.operation.PokemonOperations
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

/**
 * Created by brunogabriel on 2019-09-14.
 */
class FavoritesPresenterTest {
    private lateinit var view: FavoritesContract.View
    private lateinit var pokemonOperations: PokemonOperations
    private lateinit var favoritePresenter: FavoritesPresenter

    @Before
    fun setUp() {
        view = mock()
        pokemonOperations = mock()
        favoritePresenter = FavoritesPresenter(view, pokemonOperations)
    }

    @Test
    fun shouldShowEmptyFavorites() {
        // given
        whenever(pokemonOperations.findFavoritePokemons()).thenReturn(emptyList())

        // when
        favoritePresenter.onViewWillAppear()

        // then
        verify(view).showEmptyFavorites()
    }

    @Test
    fun shouldShowFavorites() {
        // given
        val favorites = listOf(Pokemon(1L), Pokemon(2L))
        whenever(pokemonOperations.findFavoritePokemons()).thenReturn(favorites)

        // when
        favoritePresenter.onViewWillAppear()

        // then
        verify(view).showFavorites(favorites)
    }
}
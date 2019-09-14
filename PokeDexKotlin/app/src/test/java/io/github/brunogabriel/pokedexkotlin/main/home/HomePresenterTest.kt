package io.github.brunogabriel.pokedexkotlin.main.home

import com.nhaarman.mockitokotlin2.*
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.operation.PokemonOperations
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

/**
 * Created by brunogabriel on 2019-09-14.
 */
class HomePresenterTest {
    private lateinit var view: HomeContract.View
    private lateinit var pokemonOperations: PokemonOperations
    private lateinit var homePresenter: HomePresenter

    @Before
    fun setUp() {
        view = mock()
        pokemonOperations = mock()
        homePresenter = HomePresenter(view, pokemonOperations, Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun shouldShowEmptyListWhenInitialize() {
        // given
        whenever(pokemonOperations.findPokemonList()).thenReturn(Observable.just(emptyList()))

        // when
        homePresenter.initialize()

        // then
        inOrder(view) {
            verify(view).showLoading()
            verify(view).showEmptyList()
            verify(view).dismissLoading()
        }
    }

    @Test
    fun shouldShowErrorWhenInitialize() {
        // given
        whenever(pokemonOperations.findPokemonList()).thenReturn(Observable.error(Exception()))

        // when
        homePresenter.initialize()

        // then
        verify(view).showError()
    }

    @Test
    fun shouldShowPokemonListWhenInitialize() {
        // given
        val pokemonList = listOf(Pokemon(), Pokemon())
        whenever(pokemonOperations.findPokemonList()).thenReturn(Observable.just(pokemonList))

        // when
        homePresenter.initialize()

        // then
        inOrder(view) {
            verify(view).showLoading()
            verify(view).showPokemonList(pokemonList)
            verify(view).dismissLoading()
        }
    }

    @Test
    fun shouldUpdatePokemonAtPositionWhenOnPokemonFavoriteAction() {
        // given
        val pokemon = Pokemon(100L, favorite = false)
        val position = 2

        // when
        homePresenter.onPokemonFavoriteAction(pokemon, position)

        // then
        verify(pokemonOperations).saveOrUpdatePokemon(check {
            it.favorite && it.number == 100L
        })
        verify(view).updatePokemonAtPosition(check { it.favorite && it.number == 100L }, eq(position))
    }

    @Test(expected = Test.None::class)
    fun shouldDisposeDisposableWhenOnDestroyView() {
        // when
        homePresenter.onDestroyView()
    }
}
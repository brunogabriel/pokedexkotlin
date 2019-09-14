package io.github.brunogabriel.pokedexkotlin.details

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
class PokemonDetailsPresenterTest {
    companion object {
        private const val number = 100L
        private const val name = "charmander"
    }
    private lateinit var view: PokemonDetailsContract.View
    private lateinit var pokemonOperations: PokemonOperations
    private lateinit var presenter: PokemonDetailsPresenter

    @Before
    fun setUp() {
        view = mock()
        pokemonOperations = mock()
        presenter = PokemonDetailsPresenter(view, number, pokemonOperations,
            Schedulers.trampoline(), Schedulers.trampoline())
    }

    @Test
    fun shouldShowPokemonResumeWhenInitialize() {
        // given
        val pokemon = Pokemon(number, name)
        whenever(pokemonOperations.findPokemon(number)).thenReturn(pokemon)
        whenever(pokemonOperations.findPokemonDetails(number)).thenReturn(Observable.just(pokemon))

        // when
        presenter.initialize()

        // then
        verify(view).showPokemonResume(number, name, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/100.png")
    }

    @Test
    fun shouldShowPokemonDetailsWhenInitialize() {
        // given
        val pokemon = Pokemon(number, name)
        val pokemonDetails = Pokemon(number, name, favorite = true)
        whenever(pokemonOperations.findPokemon(number)).thenReturn(pokemon)
        whenever(pokemonOperations.findPokemonDetails(number)).thenReturn(Observable.just(pokemonDetails))

        // when
        presenter.initialize()

        // then
        verify(view).showPokemonDetails(pokemonDetails)
    }

    @Test
    fun shouldShowErrorGettingPokemonDetailsWhenInitialize() {
        // given
        val pokemon = Pokemon(number, name)
        whenever(pokemonOperations.findPokemon(number)).thenReturn(pokemon)
        whenever(pokemonOperations.findPokemonDetails(number)).thenReturn(Observable.error(Exception("Any exception")))

        // when
        presenter.initialize()

        // then
        verify(view).showPokemonResume(number, name, "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/100.png")
        verify(view).showErrorGettingPokemonDetails()
        verify(view, never()).showPokemonDetails(any())
    }

    @Test(expected = Test.None::class)
    fun shouldDisposeDisposableWhenOnDestroyView() {
        // when
        presenter.onDestroyView()
    }
}
package io.github.brunogabriel.pokedexkotlin.main.home

import io.github.brunogabriel.pokedexkotlin.database.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.repository.PokemonRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by brunogabriel on 2019-10-10.
 */
class HomePresenter(
    private val view: HomeContract.View,
    private val pokemonRepository: PokemonRepository,
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler
) : HomeContract.Presenter {
    private val compositeDisposable = CompositeDisposable()

    override fun initialize() {
        requestData()
    }

    override fun onClickAtTryAgain() {
        requestData()
    }

    override fun onViewWillFinish() {
        compositeDisposable.clear()
    }

    override fun onFavoriteClicked(pokemon: Pokemon) {
        pokemon.favorite = !pokemon.favorite
        compositeDisposable.add(
            pokemonRepository.updatePokemon(pokemon)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe()
        )
    }

    private fun requestData() {
        compositeDisposable.clear()
        compositeDisposable.add(
            pokemonRepository.findPokemons()
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .doOnSubscribe { view.showLoading() }
                .subscribe({
                    view.dismissLoading()
                    if (it.isNotEmpty()) {
                        view.showPokemons(it)
                    } else {
                        view.showTryAgain()
                    }
                }, {
                    view.dismissLoading()
                    view.showTryAgain()
                })
        )
    }
}
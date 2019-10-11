package io.github.brunogabriel.pokedexkotlin.main.home

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

    private fun requestData() {
        compositeDisposable.add(
            pokemonRepository.findPokemons()
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .doOnSubscribe {

                }
                .doAfterTerminate {

                }
                .subscribe({
                    if (it.isNotEmpty()) {
                        view.showPokemons(it)
                    } else {
                        val x = 10
                    }
                }, {
                    val y = 12
                })
        )
    }
}
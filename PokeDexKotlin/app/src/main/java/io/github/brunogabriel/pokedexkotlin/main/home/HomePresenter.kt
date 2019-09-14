package io.github.brunogabriel.pokedexkotlin.main.home

import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.operation.PokemonOperations
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by brunogabriel on 2019-08-31.
 */
class HomePresenter(
    private val view: HomeContract.View,
    private val pokemonOperations: PokemonOperations = PokemonOperations(),
    private val subscriberScheduler: Scheduler = Schedulers.io(),
    private val observerScheduler: Scheduler = AndroidSchedulers.mainThread()
) : HomeContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun initialize() {
        compositeDisposable.add(pokemonOperations.findPokemonList()
            .subscribeOn(subscriberScheduler)
            .observeOn(observerScheduler)
            .doOnSubscribe { view.showLoading() }
            .doOnTerminate { view.dismissLoading() }
            .subscribe({
                if (it.isEmpty()) {
                    view.showEmptyList()
                } else {
                    view.showPokemonList(it)
                }
            }, {
                view.showError()
            })
        )
    }

    override fun onPokemonFavoriteAction(pokemon: Pokemon, position: Int) {
        pokemon.favorite = !pokemon.favorite
        pokemonOperations.saveOrUpdatePokemon(pokemon)
        view.updatePokemonAtPosition(pokemon, position)
    }

    override fun onDestroyView() {
        compositeDisposable.dispose()
    }
}

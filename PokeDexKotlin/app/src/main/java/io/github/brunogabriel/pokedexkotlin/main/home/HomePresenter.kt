package io.github.brunogabriel.pokedexkotlin.main.home

import io.github.brunogabriel.pokedexkotlin.shared.model.PokemonServiceResponse
import io.github.brunogabriel.pokedexkotlin.shared.networking.PokemonService
import io.github.brunogabriel.pokedexkotlin.shared.operation.PokemonOperations
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 * Created by brunogabriel on 2019-08-31.
 */
class HomePresenter(private val view: HomeContract.View,
                    private val pokemonService: PokemonService,
                    private val pokemonOperations: PokemonOperations = PokemonOperations(),
                    private val subscriberScheduler: Scheduler = Schedulers.io(),
                    private val observerScheduler: Scheduler = AndroidSchedulers.mainThread()) : HomeContract.Presenter {

    override fun initialize() {
        pokemonService.findPokemons()
            .subscribeOn(subscriberScheduler)
            .doOnNext { response ->
                response.body()?.results?.forEach {
                    it.number = pokemonOperations.findPokemonNumber(it)
                }
            }
            .observeOn(observerScheduler)
            .subscribe({
                // TODO: show pokemons from realm
                if (it.isSuccessful && !it.body()?.results.isNullOrEmpty()) {
                    view.showPokemons(it.body()!!.results)
                }
            }, {
                // TODO: error
            })
    }
}

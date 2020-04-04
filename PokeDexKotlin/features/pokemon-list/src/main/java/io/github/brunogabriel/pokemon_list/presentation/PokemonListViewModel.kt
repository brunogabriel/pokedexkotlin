package io.github.brunogabriel.pokemon_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.brunogabriel.core_app.ui.UiState
import io.github.brunogabriel.core_app.ui.UiStateSingle
import io.github.brunogabriel.core_app.viewmodel.BaseViewModel
import io.github.brunogabriel.domain.entities.Pokemon
import io.github.brunogabriel.domain.usecases.FetchPokemonUseCases
import io.reactivex.Scheduler

/**
 * Created by bruno on 28/02/20
 */
class PokemonListViewModel(
    private val fetchPokemonUseCases: FetchPokemonUseCases,
    private val uiScheduler: Scheduler
) : BaseViewModel() {
    private val _state = MutableLiveData<UiState<List<Pokemon>>>().apply {
        value = UiState.Loading
    }
    val state: LiveData<UiState<List<Pokemon>>>
        get() = _state

    fun fetchPokemons(forceUpdate: Boolean = false) {
        addDisposable(
            fetchPokemonUseCases.fetchPokemons(forceUpdate).compose(UiStateSingle())
                .observeOn(uiScheduler)
                .subscribe({
                    _state.postValue(it)
                }, {
                })
        )
    }
}

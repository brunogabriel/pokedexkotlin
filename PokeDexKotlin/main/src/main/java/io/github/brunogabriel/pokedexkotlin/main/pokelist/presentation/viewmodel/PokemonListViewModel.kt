package io.github.brunogabriel.pokedexkotlin.main.pokelist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.brunogabriel.pokedexkotlin.main.pokelist.domain.models.PokemonVO
import io.github.brunogabriel.pokedexkotlin.main.pokelist.domain.PokemonListUseCase
import io.github.brunogabriel.pokedexkotlin.shared.api.ApiResponse
import io.github.brunogabriel.pokedexkotlin.shared.coroutines.AppDispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(
    private val useCase: PokemonListUseCase,
    private val appDispatchers: AppDispatchers
) : ViewModel() {
    private var offset = 0
    private var limit = 100

    val pokemonsState = MutableStateFlow<ApiResponse<List<PokemonVO>>>(
        ApiResponse.None
    )

    fun loadData() {
        viewModelScope.launch {
            pokemonsState.value = ApiResponse.Loading
            try {
                val response = withContext(appDispatchers.io) {
                    useCase.fetchAll(limit, offset, false)
                }
                pokemonsState.value = ApiResponse.Success(response)
            } catch (exception: Exception) {
                pokemonsState.value = ApiResponse.Error(exception)
            }
        }
    }
}
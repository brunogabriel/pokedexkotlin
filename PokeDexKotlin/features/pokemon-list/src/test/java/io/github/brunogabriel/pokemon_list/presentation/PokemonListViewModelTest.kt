package io.github.brunogabriel.pokemon_list.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.github.brunogabriel.core_app.ui.UiState
import io.github.brunogabriel.domain.entities.Pokemon
import io.github.brunogabriel.domain.usecases.FetchPokemonUseCases
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by bruno on 10/03/20
 */
class PokemonListViewModelTest {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @RelaxedMockK
    private lateinit var useCase: FetchPokemonUseCases
    private lateinit var viewModel: PokemonListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = PokemonListViewModel(useCase, Schedulers.trampoline())
    }

    @Test
    fun `should emit value when fetch pokemons`() {
        val pokemons = listOf(Pokemon(1, "Bulbasaur", "url"))
        every { useCase.fetchPokemons(any()) } returns Single.just(pokemons)
        val observerTest = mockk<Observer<UiState<List<Pokemon>>>>(relaxed = true)
        viewModel.state.observeForever(observerTest)
        viewModel.fetchPokemons(true)
        verify {
            observerTest.onChanged(UiState.Success(pokemons))
        }
    }
}

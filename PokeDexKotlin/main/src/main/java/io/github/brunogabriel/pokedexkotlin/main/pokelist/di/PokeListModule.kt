package io.github.brunogabriel.pokedexkotlin.main.pokelist.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.PokemonListRepository
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.PokemonListRepositoryImpl
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.PokemonListDatabase
import io.github.brunogabriel.pokedexkotlin.main.pokelist.data.service.PokemonListService
import io.github.brunogabriel.pokedexkotlin.main.pokelist.domain.PokemonListUseCase
import io.github.brunogabriel.pokedexkotlin.main.pokelist.domain.PokemonListUseCaseImpl
import io.github.brunogabriel.pokedexkotlin.main.pokelist.presentation.fragment.PokeListFragment
import io.github.brunogabriel.pokedexkotlin.main.pokelist.presentation.viewmodel.PokemonListViewModel
import io.github.brunogabriel.pokedexkotlin.shared.factory.ViewModelKey
import retrofit2.Retrofit

@Module(
    includes = [
        PokeListFragmentModule::class
    ]
)
internal class PokeListModule

@Module
internal abstract class PokeListFragmentModule {
    @ContributesAndroidInjector(
        modules = [
            PokemonListViewModelModule::class
        ]
    )
    abstract fun bindFragment(): PokeListFragment
}

@Module(
    includes = [
        PokemonListDependenciesModule::class
    ]
)
internal abstract class PokemonListViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PokemonListViewModel::class)
    abstract fun bindViewModel(viewModel: PokemonListViewModel): ViewModel
}

@Module
internal abstract class PokemonListDependenciesModule {
    @Binds
    abstract fun bindRepository(repository: PokemonListRepositoryImpl): PokemonListRepository

    @Binds
    abstract fun bindUseCase(useCase: PokemonListUseCaseImpl): PokemonListUseCase

    companion object {
        @Provides
        fun providesService(retrofit: Retrofit) =
            retrofit.create(PokemonListService::class.java)

        @Provides
        fun providesDatabase(context: Context) =
            Room.databaseBuilder(context, PokemonListDatabase::class.java, "db-pokemon-list")
                .build()

        @Provides
        fun providesPokemonListDao(pokemonListDatabase: PokemonListDatabase) =
            pokemonListDatabase.pokemonListDao()
    }
}
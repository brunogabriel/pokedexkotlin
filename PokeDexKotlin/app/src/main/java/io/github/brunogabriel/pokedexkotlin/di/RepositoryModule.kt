package io.github.brunogabriel.pokedexkotlin.di

import io.github.brunogabriel.pokedexkotlin.repository.PokemonRepository
import io.github.brunogabriel.pokedexkotlin.repository.PokemonRepositoryImpl
import io.github.brunogabriel.pokedexkotlin.shared.networking.PokemonService
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by brunogabriel on 2019-10-10.
 */
val repositoryModule = module {
    factory<PokemonRepository> {
        val retrofit = get<Retrofit>()
        PokemonRepositoryImpl(get(), retrofit.create(PokemonService::class.java), get())
    }
}
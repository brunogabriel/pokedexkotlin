package io.github.brunogabriel.domain.di

import io.github.brunogabriel.domain.usecases.FetchPokemonUseCases
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

/**
 * Created by bruno on 27/02/20
 */
private val useCasesModule = module {
    factory {
        FetchPokemonUseCases(
            repository = get(),
            scheduler = Schedulers.io()
        )
    }
}

val domainModule = listOf(useCasesModule)


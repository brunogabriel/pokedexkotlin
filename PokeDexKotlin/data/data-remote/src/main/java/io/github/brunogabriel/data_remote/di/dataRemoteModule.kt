package io.github.brunogabriel.data_remote.di

import androidx.annotation.VisibleForTesting
import io.github.brunogabriel.data_core.source.PokemonRemoteDataSource
import io.github.brunogabriel.data_remote.BuildConfig
import io.github.brunogabriel.data_remote.service.PokemonListService
import io.github.brunogabriel.data_remote.source.PokemonRemoteDataSourceImplementation
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by bruno on 27/02/20
 */
@VisibleForTesting
var urlTest: String? = null

val dataRemoteModule = module {
    factory {
        OkHttpClient.Builder()
            .readTimeout(60L, TimeUnit.SECONDS)
            .connectTimeout(40L, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(urlTest ?: BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    factory<PokemonRemoteDataSource> {
        PokemonRemoteDataSourceImplementation(
            service = get<Retrofit>().create(PokemonListService::class.java)
        )
    }
}

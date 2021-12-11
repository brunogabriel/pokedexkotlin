package io.github.brunogabriel.pokedexkotlin.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import io.github.brunogabriel.pokedexkotlin.network.BuildConfig
import io.github.brunogabriel.pokedexkotlin.network.di.qualifiers.BaseUrl
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(
    includes = [
        OkHttpModule::class
    ]
)
internal class RetrofitModule {
    companion object {
        @Singleton
        @Provides
        @BaseUrl
        fun providesBaseUrl(): String = BuildConfig.BASE_URL

        @Singleton
        @Provides
        @ExperimentalSerializationApi
        fun providesRetrofit(
            @BaseUrl baseUrl: String,
            client: OkHttpClient
        ): Retrofit {
            val json = Json {
                ignoreUnknownKeys = true
            }
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
        }
    }
}

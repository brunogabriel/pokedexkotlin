package io.github.brunogabriel.pokedexkotlin.network.di

import dagger.Module
import dagger.Provides
import io.github.brunogabriel.pokedexkotlin.network.di.qualifiers.Chucker
import io.github.brunogabriel.pokedexkotlin.network.di.qualifiers.Logging
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(
    includes = [InterceptorsModule::class]
)
internal abstract class OkHttpModule {
    companion object {
        @Singleton
        @Provides
        fun providesOkHttp(
            @Chucker chucker: Interceptor,
            @Logging logging: Interceptor
        ): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(chucker)
                .addInterceptor(logging)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .build()

        private const val READ_TIMEOUT = 60L
        private const val WRITE_TIMEOUT = 30L
        private const val CONNECTION_TIMEOUT = 30L
    }
}
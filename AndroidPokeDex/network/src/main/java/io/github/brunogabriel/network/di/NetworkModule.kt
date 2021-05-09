package io.github.brunogabriel.network.di

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.brunogabriel.network.BuildConfig
import io.github.brunogabriel.shared.rx.ApplicationSchedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val CONNECTION_TIMEOUT = 60L
    private const val READ_TIMEOUT = 60L
    private const val WRITE_TIMEOUT = 60L

    @Provides
    @Named("BaseUrl")
    fun providesBaseUrl(): String = BuildConfig.API_URL

    @Provides
    @Named("LoggingInterceptor")
    fun providesLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Named("ChuckInterceptor")
    fun providesChuckInterceptor(@ApplicationContext context: Context): Interceptor =
        ChuckInterceptor(context)

    @Provides
    fun providesOkHttpClient(
        @Named("LoggingInterceptor")
        loggingInterceptor: Interceptor,
        @Named("ChuckInterceptor")
        chuckInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(chuckInterceptor)
        .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        @Named("BaseUrl") baseUrl: String,
        applicationSchedulers: ApplicationSchedulers
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(
                    applicationSchedulers.ioScheduler
                )
            )
            .build()
}
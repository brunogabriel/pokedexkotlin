package io.github.brunogabriel.pokedexkotlin.shared.networking

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import io.github.brunogabriel.pokedexkotlin.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by brunogabriel on 2019-08-29.
 */
object RetrofitManager {
    private const val READ_TIMEOUT = 120L
    private const val CONNECT_TIMEOUT = 60L
    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient

    fun initialize(context: Context, baseUrl: String = BuildConfig.BASE_URL) {
        if (!::retrofit.isInitialized) {
            okHttpClient = OkHttpClient.Builder()
                .addInterceptor(ChuckInterceptor(context))
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    fun <T> createService(serviceClazz: Class<T>): T = retrofit.create(serviceClazz)
}
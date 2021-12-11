package io.github.brunogabriel.pokedexkotlin.main.presentation.activity

import android.os.Bundle
import android.widget.TextView
import dagger.android.support.DaggerAppCompatActivity
import io.github.brunogabriel.pokedexkotlin.main.R
import io.github.brunogabriel.pokedexkotlin.main.presentation.viewmodel.MainViewModel
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.mainText).text = viewModel.getText()

        poc()
    }

    // POC
    @Inject
    lateinit var retrofit: Retrofit

    @Serializable
    data class Pokemon(
        val name: String,
        val weight: Int
    )

    private fun poc() {
        retrofit.create(PokeService::class.java).fetch().enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                val x = 10
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                val x = 10
            }

        })
    }

    interface PokeService {
        @GET("pokemon/1")
        fun fetch(): Call<Pokemon>
    }
}
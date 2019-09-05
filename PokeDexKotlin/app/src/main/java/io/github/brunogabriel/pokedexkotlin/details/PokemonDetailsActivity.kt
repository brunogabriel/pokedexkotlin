package io.github.brunogabriel.pokedexkotlin.details

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.shared.extensions.loadImage
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import kotlinx.android.synthetic.main.activity_pokemon_details.*

/**
 * Created by brunogabriel on 2019-09-01.
 */
class PokemonDetailsActivity : AppCompatActivity(), PokemonDetailsContract.View {
    companion object {
        const val POKEMON_NUMBER = "POKEMON_NUMBER"
    }
    override lateinit var presenter: PokemonDetailsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)
        toolbar.apply {
            setSupportActionBar(this)
            setNavigationOnClickListener { onBackPressed() }
        }
        presenter =
            PokemonDetailsPresenter(this, intent.extras.getLong(POKEMON_NUMBER, 0L)).apply {
            initialize()
        }
    }

    @SuppressLint("DefaultLocale")
    override fun showPokemonResume(number: Long, name: String?, imageUrl: String) {
        pokemon_image.loadImage(imageUrl) { palette ->
            val vibrantRgb = palette?.vibrantSwatch?.rgb
            val dominantRgb = palette?.dominantSwatch?.rgb
            val lightVibrantRgb = palette?.lightVibrantSwatch?.rgb

            if (vibrantRgb != null) {
                root_view.setBackgroundColor(vibrantRgb)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val window = window
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.statusBarColor = vibrantRgb
                }
            }
             toolbar.apply {
                 setSupportActionBar(this)
                 toolbar.navigationIcon = getDrawable(R.drawable.ic_close)?.apply {
                     val toolbarIconColor = if (dominantRgb != vibrantRgb) {
                         dominantRgb ?: 0
                     } else {
                         lightVibrantRgb ?: 0
                     }
                     setColorFilter(toolbarIconColor, PorterDuff.Mode.SRC_ATOP)
                     setNavigationOnClickListener { onBackPressed() }
                 }
             }
        }
        pokemon_name_text.text =  String.format(getString(R.string.pokemon_details_name_pattern, number, name?.capitalize()))
    }

    override fun showPokemonDetails(pokemon: Pokemon) {
        pokemon_height_text.visibility = View.VISIBLE
        pokemon_back_shiny_image.visibility = View.VISIBLE
        pokemon_front_shiny_image.visibility = View.VISIBLE

        pokemon_height_text.text = getString(R.string.pokemon_details_height_pattern,
            pokemon.height ?: 0)
        pokemon_back_shiny_image.loadImage(pokemon.sprites?.backShiny)
        pokemon_front_shiny_image.loadImage(pokemon.sprites?.frontShiny)
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun showErrorGettingPokemonDetails() {
    }
}
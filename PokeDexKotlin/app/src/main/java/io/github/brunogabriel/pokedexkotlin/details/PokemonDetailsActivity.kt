package io.github.brunogabriel.pokedexkotlin.details

import android.annotation.SuppressLint
import android.graphics.Outline
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewOutlineProvider
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.shared.extensions.loadImage
import io.github.brunogabriel.pokedexkotlin.shared.extensions.toDP
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import kotlinx.android.synthetic.main.activity_pokemon_details.*
import kotlinx.android.synthetic.main.view_error_try_again.*
import kotlinx.android.synthetic.main.view_loading.*

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
        changeCardView()
        presenter =
            PokemonDetailsPresenter(this,
                intent?.extras?.getLong(POKEMON_NUMBER, 0L) ?: 0L).apply {
                initialize()
            }
    }

    private fun changeCardView() {
        // apply card corner radius only in top
        content_view.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                outline?.setRoundRect(
                    0,
                    0,
                    view!!.width,
                    (view.height + 56.toDP()),
                    56.toDP().toFloat()
                )
            }
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
        pokemon_name_text.text = String.format(
            getString(
                R.string.pokemon_details_name_pattern,
                number,
                name?.capitalize()
            )
        )
    }

    override fun showPokemonDetails(pokemon: Pokemon) {
        pokemon.height?.let { height ->
            pokemon_height_text.apply {
                visibility = View.VISIBLE
                text = getString(
                    R.string.pokemon_details_height_pattern,
                    height
                )
            }
        }

        pokemon.weight?.let { weight ->
            pokemon_weight_text.apply {
                visibility = View.VISIBLE
                text = getString(
                    R.string.pokemon_details_weight_pattern,
                    weight
                )
            }
        }

        pokemon_back_shiny_image.apply {
            visibility = View.VISIBLE
            loadImage(pokemon.sprites?.backShiny)
        }

        pokemon_front_shiny_image.apply {
            visibility = View.VISIBLE
            loadImage(pokemon.sprites?.frontShiny)
        }

        pokemon_types_chip_group.visibility = View.VISIBLE

        pokemon.types
            .map { it.type }
            .forEach { pokemonType ->
                val resource = pokemonType?.findResource()
                pokemon_types_chip_group.addView(Chip(pokemon_types_chip_group.context).apply {
                    text = pokemonType?.name
                    if (resource != null) {
                        chipIcon = getDrawable(resource)
                    }
                    isClickable = false
                    isFocusable = false
                })
            }
    }

    override fun showLoading() {
        view_loading.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        view_loading.visibility = View.GONE
    }

    override fun showErrorGettingPokemonDetails() {
        view_error_try_again.apply {
            visibility = View.VISIBLE
            try_again_button.setOnClickListener {
                visibility = View.GONE
                presenter.initialize()
            }
        }
    }
}
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
import io.github.brunogabriel.pokedexkotlin.shared.model.PokemonType
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
        changeCardView()
        presenter =
            PokemonDetailsPresenter(this, intent.extras.getLong(POKEMON_NUMBER, 0L)).apply {
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
            loadImage(pokemon?.sprites?.backShiny)
        }

        pokemon_front_shiny_image.apply {
            visibility = View.VISIBLE
            loadImage(pokemon?.sprites?.frontShiny)
        }

        pokemon_types_chip_group.visibility = View.VISIBLE
        pokemon.types.forEach { pokemonType ->
            val resource = convertToResource(pokemonType)
            pokemon_types_chip_group.addView(Chip(pokemon_types_chip_group.context).apply {
                text = pokemonType?.type?.name
                if (resource != null) {
                    chipIcon = getDrawable(resource)
                }
                isClickable = false
                isFocusable = false
            })
        }
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {
    }

    override fun showErrorGettingPokemonDetails() {
    }

    private fun convertToResource(pokemonType: PokemonType): Int? {
        return when (pokemonType.type?.name) {
            "bug" -> R.drawable.bug
            "dark" -> R.drawable.dark
            "dragon" -> R.drawable.dragon
            "electric" -> R.drawable.electric
            "fairy" -> R.drawable.fairy
            "fighting" -> R.drawable.fight
            "fire" -> R.drawable.fire
            "flying" -> R.drawable.flying
            "ghost" -> R.drawable.ghost
            "grass" -> R.drawable.grass
            "ground" -> R.drawable.ground
            "ice" -> R.drawable.ic_close
            "normal" -> R.drawable.normal
            "poison" -> R.drawable.poison
            "psychic" -> R.drawable.psychic
            "rock" -> R.drawable.rock
            "steel" -> R.drawable.steel
            "water" -> R.drawable.water
            else -> null
        }
    }
}
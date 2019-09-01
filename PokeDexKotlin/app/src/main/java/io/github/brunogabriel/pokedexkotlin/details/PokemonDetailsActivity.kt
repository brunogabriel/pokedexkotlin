package io.github.brunogabriel.pokedexkotlin.details

import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.shared.database.PokemonRepository
import io.github.brunogabriel.pokedexkotlin.shared.extensions.loadImage
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
        onlyTest()
    }

    private fun onlyTest() {
        pokemon_image.loadImage(
            PokemonRepository().findByNumber(
                intent.extras.getLong(
                    POKEMON_NUMBER, 0
                )
            )!!.findSpriteUrl()
        ) { palette ->

            val (vibrantDarkColor, vibrantColor) = Pair(palette?.darkMutedSwatch?.rgb,
                palette?.vibrantSwatch?.rgb)

            if (vibrantColor != null) {
                root_view.setBackgroundColor(vibrantColor)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val window = window
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    window.statusBarColor = vibrantColor
                }
            }

            if (vibrantDarkColor != null) {
                toolbar.apply {
                    setSupportActionBar(this)
                    toolbar.navigationIcon = getDrawable(R.drawable.ic_close).apply {
                        setColorFilter(vibrantDarkColor, PorterDuff.Mode.SRC_ATOP)
                        setNavigationOnClickListener { onBackPressed() }
                    }
                }
            }
        }
    }


}
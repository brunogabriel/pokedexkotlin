package io.github.brunogabriel.styleguide.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.palette.graphics.Palette
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import io.github.brunogabriel.shared.extensions.load
import io.github.brunogabriel.styleguide.R
import io.github.brunogabriel.styleguide.databinding.ViewPokemonCardBinding
import io.github.brunogabriel.styleguide.components.models.PokemonCardViewModel
import java.lang.Exception

class PokemonCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val binding: ViewPokemonCardBinding =
        DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_pokemon_card,
            this,
            true
        )

    fun bindPokemonCardView(model: PokemonCardViewModel): PokemonCardView {
        with(binding) {
            pokemonNameTextView.text = model.name
            pokemonNumberTextView.text = model.number

            pokemonImageView.load(model.imageUrl) { palette ->
                if (palette != null) {
                    palette.vibrantSwatch?.let {
                        pokemonMaterialCardView.setCardBackgroundColor(it.rgb)
                    }
                    palette.lightVibrantSwatch?.let {
                        pokemonTypeOneTagView.setTagBackgroundColor(it.rgb)
                        pokemonTypeTwoTagView.setTagBackgroundColor(it.rgb)
                    }
                }
            }
            pokemonTypeOneTagView.bindTagText(model.type1)
            model.type2?.let { type2 ->
                pokemonTypeTwoTagView.visibility = View.VISIBLE
                pokemonTypeTwoTagView.bindTagText(type2)
            }
        }
        return this
    }


}


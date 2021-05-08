package io.github.brunogabriel.styleguide.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import io.github.brunogabriel.styleguide.R
import io.github.brunogabriel.styleguide.databinding.ViewPokemonCardBinding
import io.github.brunogabriel.styleguide.components.models.PokemonCardViewModel

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


            pokemonMaterialCardView.setCardBackgroundColor(Color.GREEN)

            // TODO: mudar para um efeito com a cor clara
            pokemonTypeOneTagView.bindTagText(model.type1)
                .setTagBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        android.R.color.holo_green_dark
                    )
                )

            // TODO: mudar para um efeito com a cor clara
            model.type2?.let { type2 ->
                pokemonTypeTwoTagView.visibility = View.VISIBLE
                pokemonTypeTwoTagView.bindTagText(type2)
                    .setTagBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            android.R.color.holo_green_dark
                        )
                    )
            }
        }
        return this
    }

}


package io.github.brunogabriel.androidpokedex.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.github.brunogabriel.androidpokedex.R
import io.github.brunogabriel.styleguide.components.models.PokemonCardViewModel
import kotlinx.android.synthetic.main.activity_sample.*

class SampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        tagView.bindTagText("Poison")
            .setTagTextColor(ContextCompat.getColor(this, R.color.black))
            .setTagBackgroundColor(ContextCompat.getColor(this, R.color.white))

        card1.bindPokemonCardView(
            PokemonCardViewModel(
                name = "Bulbasaur",
                number = "#001",
                imageUrl = "https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/thumbnails-compressed/001.png",
                type1 = "Grass",
                type2 = "Poison"
            )
        )

        card2.bindPokemonCardView(
            PokemonCardViewModel(
                name = "Charmander",
                number = "#004",
                imageUrl = "https://raw.githubusercontent.com/HybridShivam/Pokemon/master/assets/thumbnails-compressed/004.png",
                type1 = "Fire",
                type2 = null
            )
        )
    }
}
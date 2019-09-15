package io.github.brunogabriel.pokedexkotlin.details

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.details.PokemonDetailsActivity.Companion.POKEMON_NUMBER
import io.github.brunogabriel.pokedexkotlin.helper.createRealmInMemory
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.model.PokemonType
import io.github.brunogabriel.pokedexkotlin.shared.model.Type
import io.realm.Realm
import io.realm.RealmList
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by brunogabriel on 2019-09-14.
 */
class PokemonDetailsActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(PokemonDetailsActivity::class.java, true, false)

    @Before
    fun setUp() {
        createRealmInMemory()
        savePokemonListInMemory()
    }

    @Test
    fun shouldShowPokemonDetails() {
        // given
        activityRule.launchActivity(Intent().putExtra(POKEMON_NUMBER, 6L))

        // then
        onView(withId(R.id.pokemon_name_text))
            .check(matches(withText(containsString("Charizard"))))
        onView(withId(R.id.pokemon_height_text))
            .check(matches(withText(containsString("99"))))
        onView(withId(R.id.pokemon_weight_text))
            .check(matches(withText(containsString("88"))))
        onView(allOf(withText(containsString("fire")))).check(matches(isDisplayed()))
    }

    private fun savePokemonListInMemory() {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                val pokemon = Pokemon(
                    6L,
                    "Charizard",
                    "",
                    99,
                    88,
                    types = RealmList<PokemonType>().apply {
                        add(PokemonType(type = Type("fire")))
                    },
                    details = true
                )
                it.insertOrUpdate(pokemon)
            }
        }
    }
}
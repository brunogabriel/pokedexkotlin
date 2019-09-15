package io.github.brunogabriel.pokedexkotlin.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.assertions.atPosition
import io.github.brunogabriel.pokedexkotlin.assertions.hasItemCount
import io.github.brunogabriel.pokedexkotlin.helper.createRealmInMemory
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.realm.Realm
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by brunogabriel on 2019-09-14.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Before
    fun setUp() {
        createRealmInMemory()
        savePokemonListInMemory()
    }

    @Test
    fun shouldShowTabItems() {
        // given
        activityRule.launchActivity(null)

        // then
        onView(withId(R.id.bottom_navigation_view)).check(matches(isDisplayed()))
        onView(withId(R.id.home_item)).check(matches(isDisplayed()))
        onView(withId(R.id.favorites_item)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowPokemonList() {
        // given
        activityRule.launchActivity(null)

        // when
        onView(withId(R.id.home_item)).perform(click())

        // then
        onView(withId(R.id.recycler_view_pokemon_list))
            .check(hasItemCount(3))
            .check(matches(atPosition(0, hasDescendant(withText("Bulbasaur")))))
            .check(matches(atPosition(1, hasDescendant(withText("Ivysaur")))))
            .check(matches(atPosition(2, hasDescendant(withText("Venusaur")))))
    }

    @Test
    fun shouldShowFavorites() {
        // given
        activityRule.launchActivity(null)

        // when
        onView(withId(R.id.favorites_item)).perform(click())


        // then
        onView(withId(R.id.recycler_view_pokemon_favorites))
            .check(hasItemCount(2))
            .check(matches(atPosition(0, hasDescendant(withText("Bulbasaur")))))
            .check(matches(atPosition(1, hasDescendant(withText("Venusaur")))))
    }

    @Test
    fun shouldShowNoFavoritesYet() {
        // given
        createRealmInMemory()
        activityRule.launchActivity(null)

        // when
        onView(withId(R.id.favorites_item)).perform(click())


        // then
        onView(withId(R.id.view_favorites)).check(matches(isDisplayed()))
    }

    private fun savePokemonListInMemory() {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction {
                it.insertOrUpdate(
                    listOf(
                        Pokemon(1L, "Bulbasaur", favorite = true),
                        Pokemon(2L, "Ivysaur", favorite = false),
                        Pokemon(3L, "Venusaur", favorite = true)
                    )
                )
            }
        }
    }
}
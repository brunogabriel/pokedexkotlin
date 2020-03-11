package io.github.brunogabriel.pokemon_list.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.junit.WireMockRule
import io.github.brunogabriel.coretest.assertion.atPosition
import io.github.brunogabriel.coretest.assertion.hasItemCount
import io.github.brunogabriel.coretest.robots.Robots
import io.github.brunogabriel.data_local.di.inMemoryDatabase
import io.github.brunogabriel.data_remote.di.urlTest
import io.github.brunogabriel.pokemon_list.R
import org.junit.Rule

/**
 * Created by bruno on 10/03/20
 */
class PokemonListActivityRobot(
    private val rule: ActivityTestRule<PokemonListActivity>
) : Robots {
    @get: Rule
    val mockServer = WireMockRule()

    override fun setup() {
        urlTest = "http://127.0.0.1:8080"
        inMemoryDatabase = true
    }

    fun showScreen() {
        rule.launchActivity(null)
    }

    fun checkNumberOfItemsDisplayed(numberOfItems: Int) {
        onView(withId(R.id.pokemons_recycler_view)).check(hasItemCount(numberOfItems))
    }

    fun checkDisplayPokemonName(position: Int, name: String) {
        onView(withId(R.id.pokemons_recycler_view))
            .check(matches(atPosition(position, hasDescendant(withText(name)))))
    }

    fun checkDisplayTryAgain() {
        mockError()
        onView(withId(R.id.try_again_view)).check(matches(isDisplayed()))
        onView(withId(R.id.try_again_button)).perform(click())
        onView(withId(R.id.try_again_view)).check(matches(isDisplayed()))
    }

    private fun mockError() {
        mockServer.stubFor(
            WireMock.get(WireMock.urlPathEqualTo("/pokemon?limit=251"))
                .willReturn(WireMock.aResponse().withStatus(404))
        )
    }
}

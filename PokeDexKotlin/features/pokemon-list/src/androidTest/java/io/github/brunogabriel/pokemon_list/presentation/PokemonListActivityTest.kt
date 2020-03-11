package io.github.brunogabriel.pokemon_list.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import io.github.brunogabriel.coretest.robots.RobotsRule
import io.github.brunogabriel.data_core.di.dataModule
import io.github.brunogabriel.data_local.database.dao.PokemonDao
import io.github.brunogabriel.data_local.di.dataLocalModule
import io.github.brunogabriel.data_local.models.PokemonCache
import io.github.brunogabriel.data_remote.di.dataRemoteModule
import io.github.brunogabriel.domain.di.domainModule
import io.github.brunogabriel.pokemon_list.di.pokemonListModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

/**
 * Created by bruno on 10/03/20
 */
@RunWith(AndroidJUnit4::class)
class PokemonListActivityTest : KoinTest {
    @get: Rule
    val activityRule = ActivityTestRule(PokemonListActivity::class.java, true, false)
    @get: Rule
    val robot = RobotsRule(PokemonListActivityRobot(activityRule))
    private val dao: PokemonDao by inject()

    @Before
    fun setup() {
        robot.setup()
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().context)
            loadKoinModules(dataModule)
            loadKoinModules(dataRemoteModule)
            loadKoinModules(dataLocalModule)
            loadKoinModules(domainModule)
            loadKoinModules(pokemonListModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun shouldShowPokemons() {
        robot {
            mockPokemons()
            showScreen()
            checkNumberOfItemsDisplayed(4)
            checkDisplayPokemonName(0, "Title1")
        }
    }

    @Test
    fun shouldShowTryAgain() {
        robot {
            showScreen()
            checkDisplayTryAgain()
        }
    }

    private fun mockPokemons() = dao.insertAll((1..4).toList().map {
        PokemonCache(it, "title$it", "url$it")
    })
}

package io.github.brunogabriel.pokedexkotlin.shared.model

import com.nhaarman.mockitokotlin2.mock
import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.github.brunogabriel.pokedexkotlin.shared.model.PokemonSprites
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by brunogabriel on 2019-09-04.
 */
class PokemonTest {
    private lateinit var pokemon: Pokemon
    private lateinit var pokemonSprites: PokemonSprites

    @Before
    fun setUp() {
        pokemonSprites = mock()
        pokemon = Pokemon(
            number = 200L,
            height = 100,
            sprites = pokemonSprites
        )
    }

    @Test
    fun shouldFindSpriteUrl() {
        // when
        assertEquals("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/200.png", pokemon.findSpriteUrl())
    }

    @Test
    fun shouldHasDetailsWhenHasHeightAndSprites() {
        // when
        assertTrue(pokemon.hasDetails())
    }

    @Test
    fun shouldHasNotDetailsWhenHasNotHeight() {
        // given
        pokemon.height = null

        // when
        assertFalse(pokemon.hasDetails())
    }

    @Test
    fun shouldHasNotDetailsWhenHasNotPokemonSprites() {
        // given
        pokemon.sprites = null

        // then
        assertFalse(pokemon.hasDetails())
    }
}
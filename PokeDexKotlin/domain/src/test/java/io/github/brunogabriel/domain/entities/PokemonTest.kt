package io.github.brunogabriel.domain.entities

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by bruno on 27/02/20
 */
class PokemonTest {
    @Test
    fun `should be equals`() {
        val number = 9
        val name = "Charizard"
        val imageUrl = "any url"
        assertThat(Pokemon(number, name, imageUrl), `is`(Pokemon(number, name, imageUrl)))
    }
}
package io.github.brunogabriel.data_remote.models

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by bruno on 27/02/20
 */
class PokemonPayloadTest {
    @Test
    fun `should be equals`() {
        val name = "Darkrai"
        val url = "any url"
        val pokemonPayload = PokemonPayload(name, url)
        assertThat(pokemonPayload, `is`(PokemonPayload(name, url)))
    }
}

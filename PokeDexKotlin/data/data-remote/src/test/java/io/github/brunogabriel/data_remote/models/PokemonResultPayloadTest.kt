package io.github.brunogabriel.data_remote.models

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by bruno on 27/02/20
 */
class PokemonResultPayloadTest {
    @Test
    fun `should be equals`() {
        val pokemonPayloads = listOf(
            PokemonPayload("Mewtwo", "150"),
            PokemonPayload("Mew", "151")
        )
        val pokemonResultPayload = PokemonResultPayload(
            2,
            pokemonPayloads
        )
        assertThat(
            pokemonResultPayload, `is`(
                PokemonResultPayload(
                    2,
                    listOf(
                        PokemonPayload("Mewtwo", "150"),
                        PokemonPayload("Mew", "151")
                    )
                )
            )
        )
    }
}

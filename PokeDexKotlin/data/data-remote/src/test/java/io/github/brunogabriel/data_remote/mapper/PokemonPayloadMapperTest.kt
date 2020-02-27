package io.github.brunogabriel.data_remote.mapper

import io.github.brunogabriel.data_remote.mapper.PokemonPayloadMapper.mapToPokemon
import io.github.brunogabriel.data_remote.models.PokemonPayload
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by bruno on 27/02/20
 */
class PokemonPayloadMapperTest {
    @Test
    fun `should convert to pokemon`() {
        val name = "Bulbasaur"
        val url = "https://pokeapi.co/api/v2/pokemon/1/"
        val payloads = listOf(
            PokemonPayload(
                name,
                url
            )
        )
        val result = mapToPokemon(payloads)
        assertThat(result.size, `is`(1))
        assertThat(result[0].number, `is`(1))
        assertThat(result[0].name, `is`(name))
        assertThat(result[0].imageUrl.endsWith("1.png"), `is`(true))
    }

    @Test
    fun `should convert to pokemon with invalid url`() {
        val name = "Mewtwo"
        val url = "https://pokeapi.co/api/v2/pokemon"
        val payloads = listOf(
            PokemonPayload(
                name,
                url
            )
        )
        val result = mapToPokemon(payloads)
        assertThat(result.size, `is`(1))
        assertThat(result[0].number, `is`(0))
        assertThat(result[0].name, `is`(name))
        assertThat(result[0].imageUrl.endsWith("0.png"), `is`(true))
    }
}
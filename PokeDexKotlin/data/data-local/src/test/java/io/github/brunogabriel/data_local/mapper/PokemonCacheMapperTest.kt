package io.github.brunogabriel.data_local.mapper

import io.github.brunogabriel.data_local.mapper.PokemonCacheMapper.mapToCache
import io.github.brunogabriel.data_local.mapper.PokemonCacheMapper.mapToPokemon
import io.github.brunogabriel.data_local.models.PokemonCache
import io.github.brunogabriel.domain.entities.Pokemon
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Created by bruno on 09/03/20
 */
class PokemonCacheMapperTest {
    @Test
    fun `should convert to pokemon`() {
        val name = "Bulbasaur"
        val url = "https://pokeapi.co/api/v2/pokemon/1/"
        val cache = listOf(
            PokemonCache(
                1,
                name,
                url
            )
        )
        val result = mapToPokemon(cache)
        assertThat(result.size, `is`(1))
        assertThat(result[0].number, `is`(1))
        assertThat(result[0].name, `is`(name))
        assertThat(result[0].imageUrl, `is`(url))
    }

    @Test
    fun `should convert to cache`() {
        val name = "Bulbasaur"
        val url = "https://pokeapi.co/api/v2/pokemon/1/"
        val number = 1
        val pokemons = listOf(
            Pokemon(
                number, name, url
            )
        )
        val result = mapToCache(pokemons)
        assertThat(result.size, `is`(1))
        assertThat(result[0].primaryKey, `is`(number))
        assertThat(result[0].name, `is`(name))
        assertThat(result[0].url, `is`(url))
    }
}

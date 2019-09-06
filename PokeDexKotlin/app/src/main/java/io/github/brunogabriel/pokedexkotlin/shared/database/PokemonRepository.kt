package io.github.brunogabriel.pokedexkotlin.shared.database

import io.github.brunogabriel.pokedexkotlin.shared.model.Pokemon
import io.realm.Realm

/**
 * Created by brunogabriel on 2019-09-01.
 */
class PokemonRepository : RealmRepository<Pokemon>() {
    fun findByNumber(number: Long): Pokemon? {
        return Realm.getDefaultInstance().use { realm ->
            realm.where(Pokemon::class.java)
                .equalTo("number", number)
                .findFirst()
                ?.let { realm.copyFromRealm(it) }
        }
    }

    fun findFavorites(): List<Pokemon> {
        return Realm.getDefaultInstance().use { realm ->
            realm.where(Pokemon::class.java)
                .equalTo("favorite", true)
                .findAll().let { realm.copyFromRealm(it) }
        }
    }
}
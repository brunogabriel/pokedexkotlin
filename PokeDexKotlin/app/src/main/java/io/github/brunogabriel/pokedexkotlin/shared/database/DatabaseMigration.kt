package io.github.brunogabriel.pokedexkotlin.shared.database

import io.realm.DynamicRealm
import io.realm.RealmMigration

/**
 * Created by brunogabriel on 2019-09-01.
 */
class DatabaseMigration : RealmMigration {
    companion object {
        const val DATABASE_VERSION = 1L
    }

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        // TODO: when database changes
    }
}
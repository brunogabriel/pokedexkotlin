package io.github.brunogabriel.pokedexkotlin.shared.database

import io.realm.Realm
import io.realm.RealmModel

/**
 * Created by brunogabriel on 2019-09-01.
 */
open class RealmRepository<T : RealmModel > {
    fun saveEntity(entity: T) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { it.insertOrUpdate(entity) }
        }
    }

    fun saveAll(entities: List<T>) {
        Realm.getDefaultInstance().use { realm ->
            realm.executeTransaction { it.insertOrUpdate(entities) }
        }
    }

    fun findAll(clazz: Class<T>): List<T> {
        return Realm.getDefaultInstance().use { realm ->
            realm.copyFromRealm(realm.where(clazz).findAll())
        }
    }

}
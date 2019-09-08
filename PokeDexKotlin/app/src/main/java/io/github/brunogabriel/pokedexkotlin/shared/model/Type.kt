package io.github.brunogabriel.pokedexkotlin.shared.model

import io.realm.RealmModel
import io.realm.annotations.RealmClass

/**
 * Created by brunogabriel on 2019-09-07.
 */
@RealmClass
open class Type(
    var name: String? = null
) : RealmModel {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
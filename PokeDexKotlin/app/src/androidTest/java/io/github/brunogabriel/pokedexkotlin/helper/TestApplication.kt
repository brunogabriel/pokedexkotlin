package io.github.brunogabriel.pokedexkotlin.helper

import android.app.Application
import io.github.brunogabriel.pokedexkotlin.shared.networking.RetrofitManager
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by brunogabriel on 2019-09-13.
 */
class TestApplication : Application() {
    companion object {
        const val DEFAULT_SERVER_URL = "http://127.0.0.1:8080"
    }
    override fun onCreate() {
        super.onCreate()
        RetrofitManager.initialize(this, DEFAULT_SERVER_URL)
        Realm.init(this)
    }
    fun createRealmInMemory() {
        val realmConfig = RealmConfiguration.Builder().name("realm.memory").build()
        Realm.deleteRealm(realmConfig)
        Realm.setDefaultConfiguration(realmConfig)
    }
}
package io.github.brunogabriel.pokedexkotlin.shared.arch

import android.app.Application
import de.jonasrottmann.realmbrowser.RealmBrowser
import io.github.brunogabriel.pokedexkotlin.shared.database.DatabaseMigration
import io.github.brunogabriel.pokedexkotlin.shared.networking.RetrofitManager
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by brunogabriel on 2019-08-31.
 */
class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitManager.initialize(this)
        initializeRealm()
    }

    private fun initializeRealm() {
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
            .schemaVersion(DatabaseMigration.DATABASE_VERSION)
            .migration(DatabaseMigration())
            .build()

        try {
            Realm.getInstance(configuration)
        } catch (_: Exception) {
            Realm.deleteRealm(configuration)
        }

        RealmBrowser.addFilesShortcut(this)
        RealmBrowser.addModelsShortcut(this, configuration)

        Realm.setDefaultConfiguration(configuration)
    }
}
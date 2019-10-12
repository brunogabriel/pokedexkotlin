package io.github.brunogabriel.pokedexkotlin.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.main.favorites.FavoritesFragment
import io.github.brunogabriel.pokedexkotlin.main.home.HomeFragment
import io.github.brunogabriel.pokedexkotlin.shared.adapter.BottomPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by brunogabriel on 2019-10-10.
 */
class MainActivity : AppCompatActivity() {

    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val favoritesFragment: FavoritesFragment by lazy { FavoritesFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        view_pager.apply {
            adapter =
                BottomPageAdapter(listOf(homeFragment, favoritesFragment), supportFragmentManager)
        }
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_item -> {
                    view_pager.setCurrentItem(0, false)
                    true
                }
                else -> {
                    view_pager.setCurrentItem(1, false)
                    true
                }
            }
        }
    }
}

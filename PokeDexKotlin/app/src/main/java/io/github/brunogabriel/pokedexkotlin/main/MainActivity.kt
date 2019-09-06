package io.github.brunogabriel.pokedexkotlin.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.main.favorites.FavoritesFragment
import io.github.brunogabriel.pokedexkotlin.main.home.HomeFragment
import io.github.brunogabriel.pokedexkotlin.shared.adapter.BottomPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by brunogabriel on 2019-08-29.
 */
class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var homeFragment: HomeFragment
    private lateinit var favoritesFragment: FavoritesFragment
    override lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        onlyTests()
    }

    private fun onlyTests() {
        homeFragment = HomeFragment()
        favoritesFragment = FavoritesFragment()

        view_pager.apply {
            adapter = BottomPageAdapter(
                listOf(homeFragment, favoritesFragment), supportFragmentManager
            )
        }
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_item -> {
                    view_pager.setCurrentItem(0, false)
                    true
                }
                R.id.favorites_item -> {
                    view_pager.setCurrentItem(1, false)
                    true
                }
                else -> false
            }
        }
    }
}
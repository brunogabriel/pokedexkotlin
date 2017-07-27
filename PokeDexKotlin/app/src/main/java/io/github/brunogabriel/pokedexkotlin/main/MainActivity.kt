package io.github.brunogabriel.pokedexkotlin.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import io.github.brunogabriel.pokedexkotlin.R
import io.github.brunogabriel.pokedexkotlin.main.favorites.FavoritesFragment
import io.github.brunogabriel.pokedexkotlin.main.list.PokemonListFragment
import io.github.brunogabriel.pokedexkotlin.main.more.MoreFragment
import io.github.brunogabriel.pokedexkotlin.shared.application.BaseActivity
import io.github.brunogabriel.pokedexkotlin.shared.ui.bind

/**
 * Created by bruno on 7/25/17.
 */
class MainActivity : BaseActivity(), MainContract.View {

    override var presenter: MainContract.Presenter? = null

    // Views
    private val toolbar by bind<Toolbar>(R.id.toolbar)
    private val viewPager by bind<ViewPager>(R.id.view_pager)
    private val bottomNavigation by bind<BottomNavigationView>(R.id.bottom_navigation)

    // Fragments
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var pokeListFragment: PokemonListFragment
    private lateinit var favoritesFragment: FavoritesFragment
    private lateinit var moreFragment: MoreFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(this)
        presenter?.start()
    }

    override fun startView() {
        // setup bottom navigation
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.action_list -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.action_favorites -> {
                    viewPager.currentItem = 1
                    true
                }
                R.id.action_more -> {
                    viewPager.currentItem = 2
                    true
                }
                else -> false
            }
        }

        setupViewPager()
    }

    fun setupViewPager() {
        // Create all fragments
        pokeListFragment = PokemonListFragment()
        favoritesFragment = FavoritesFragment()
        moreFragment = MoreFragment()

        // create adapter
        viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, listOf(pokeListFragment, favoritesFragment, moreFragment))
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = viewPagerAdapter

        // define toolbar title
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                showTitle(position)
            }
        })

        // define title
        toolbar.title = getString(R.string.pokemon)
    }

    private fun showTitle(position: Int) {
        when(position) {
            0 -> toolbar.title = getString(R.string.pokemon)
            1 -> toolbar.title = getString(R.string.favorites)
            2 -> toolbar.title = getString(R.string.more)
        }
    }

    inner class ViewPagerAdapter(fm: FragmentManager?, val fragments: List<Fragment>): FragmentPagerAdapter(fm) {
        override fun getCount() = fragments.size
        override fun getItem(position: Int) = fragments[position]
    }
}

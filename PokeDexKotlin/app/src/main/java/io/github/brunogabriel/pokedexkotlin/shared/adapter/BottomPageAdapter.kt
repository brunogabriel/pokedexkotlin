package io.github.brunogabriel.pokedexkotlin.shared.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by brunogabriel on 2019-08-29.
 */
class BottomPageAdapter(private val fragments: List<Fragment>, fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int) = fragments[position]
    override fun getCount() = fragments.size
}
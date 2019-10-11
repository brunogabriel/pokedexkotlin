package io.github.brunogabriel.pokedexkotlin.shared.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * Created by brunogabriel on 2019-08-29.
 */
class BottomPageAdapter(
    private val fragments: List<Fragment>, fragmentManager: FragmentManager
) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int) = fragments[position]
    override fun getCount() = fragments.size
}
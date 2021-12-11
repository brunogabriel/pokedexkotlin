package io.github.brunogabriel.pokedexkotlin.shared.extensions

import android.content.res.Resources.getSystem

val Int.px: Int get() = (this * getSystem().displayMetrics.density).toInt()
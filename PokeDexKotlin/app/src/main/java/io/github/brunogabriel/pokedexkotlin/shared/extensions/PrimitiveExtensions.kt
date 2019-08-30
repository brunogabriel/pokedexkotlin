package io.github.brunogabriel.pokedexkotlin.shared.extensions

import android.content.res.Resources

/**
 * Created by brunogabriel on 2019-08-29.
 */

fun Int.toDP() = (this * Resources.getSystem().displayMetrics.density).toInt()
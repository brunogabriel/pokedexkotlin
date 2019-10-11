package io.github.brunogabriel.pokedexkotlin.shared.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Created by brunogabriel on 2019-10-10.
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachRoot)
}
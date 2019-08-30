package io.github.brunogabriel.pokedexkotlin.shared.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by brunogabriel on 2019-08-29.
 */
fun ViewGroup.inflate(resource: Int, attachRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(resource, this, attachRoot)
}

fun ImageView.loadImage(url: String?) {
    Picasso.get().load(url).into(this)
}
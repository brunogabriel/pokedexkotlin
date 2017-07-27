package io.github.brunogabriel.pokedexkotlin.shared.ui

import android.app.Activity
import android.content.Context
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by bruno on 7/25/17.
 */

// Binding
private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)
fun <T: View> Fragment.bind(@IdRes idResource: Int): Lazy<T> = unsafeLazy { view?.findViewById(idResource) as T}
fun <T: View> Activity.bind(@IdRes idResource: Int): Lazy<T> = unsafeLazy { findViewById(idResource) as T}
fun <T: View> RecyclerView.ViewHolder.bind(@IdRes idResource: Int): Lazy<T> = unsafeLazy { itemView?.findViewById(idResource) as T}

// Layout Inflater
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

// Layout Inflater
val Context.picasso: Picasso get() = Picasso.with(this)

fun ImageView.load(path: String) {
    context.picasso.load(path).into(this)
}
package io.github.brunogabriel.pokedexkotlin.shared.extensions

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

fun ImageView.loadImage(url: String, cached: Boolean = false) {
    Picasso.get().load(url)
        .apply {
            if (cached) {
                networkPolicy(NetworkPolicy.OFFLINE)
            }
            this.into(this@loadImage, object : Callback {
                override fun onSuccess() {}
                override fun onError(e: Exception?) {
                    if (cached) {
                        Picasso.get().load(url).into(this@loadImage)
                    }
                }
            })
        }
}

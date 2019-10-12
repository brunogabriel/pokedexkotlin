package io.github.brunogabriel.pokedexkotlin.shared.extensions

import android.widget.ImageView
import androidx.annotation.NonNull
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import java.lang.Exception

/**
 * Created by brunogabriel on 2019-10-11.
 */
fun ImageView.loadImage(@NonNull path: String) {
    Picasso.get()
        .load(path)
        .networkPolicy(NetworkPolicy.OFFLINE)
        .into(this, object : Callback {
            override fun onSuccess() {}

            override fun onError(e: Exception?) {
                Picasso.get().load(path).into(this@loadImage)
            }
        })
}
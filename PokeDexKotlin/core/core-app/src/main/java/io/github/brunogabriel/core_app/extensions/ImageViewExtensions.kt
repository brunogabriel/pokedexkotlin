package io.github.brunogabriel.core_app.extensions

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

/**
 * Created by bruno on 27/02/20
 */
fun ImageView.loadImage(url: String) {
    Picasso.get()
        .load(url)
        .networkPolicy(NetworkPolicy.OFFLINE)
        .into(this, object : Callback {
            override fun onSuccess() {}
            override fun onError(e: Exception?) {
                Picasso.get().load(url).into(this@loadImage)
            }
        })
}

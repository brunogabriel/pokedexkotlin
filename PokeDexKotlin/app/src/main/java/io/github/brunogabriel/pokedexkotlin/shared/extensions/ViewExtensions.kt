package io.github.brunogabriel.pokedexkotlin.shared.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.palette.graphics.Palette
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

/**
 * Created by brunogabriel on 2019-08-29.
 */
fun ViewGroup.inflate(resource: Int, attachRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(resource, this, attachRoot)
}
fun ImageView.loadImage(url: String?, asyncPalette: ((Palette?) -> Unit)? = null) {
    if (asyncPalette != null) {
        val target = object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                this@loadImage.setImageBitmap(bitmap)
                bitmap?.let { Palette.from(bitmap).generate { asyncPalette(it) } }
            }
        }
        Picasso.get()
            .load(url)
            .networkPolicy(NetworkPolicy.OFFLINE)
            .into(target)
        tag = target
    } else {
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

}
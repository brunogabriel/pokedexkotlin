package io.github.brunogabriel.shared.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.palette.graphics.Palette
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class PalettePicassoTarget(
    private val imageView: ImageView,
    private val paletteAsyncListener: Palette.PaletteAsyncListener? = null
) : Target {

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        bitmap?.let { innerBitmap ->
            imageView.setImageBitmap(innerBitmap)
            paletteAsyncListener?.let { Palette.from(innerBitmap).generate(paletteAsyncListener) }
        }
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        imageView.setImageDrawable(errorDrawable)
    }

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        imageView.setImageDrawable(placeHolderDrawable)
    }
}

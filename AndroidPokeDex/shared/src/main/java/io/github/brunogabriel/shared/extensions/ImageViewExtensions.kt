package io.github.brunogabriel.shared.extensions

import android.widget.ImageView
import androidx.palette.graphics.Palette
import com.squareup.picasso.Picasso
import io.github.brunogabriel.shared.utils.PalettePicassoTarget

// TODO: in future pass placeholder + error drawable to avoid mistakes
fun ImageView.load(
    path: String,
    paletteAsyncListener: Palette.PaletteAsyncListener? = null
) {
    val target = PalettePicassoTarget(this, paletteAsyncListener)
    Picasso.get().load(path).into(target)
    tag = target
}
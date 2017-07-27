package io.github.brunogabriel.pokedexkotlin.shared.application

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.view.View
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper



/**
 * Created by bruno on 7/25/17.
 */
abstract class BaseActivity: AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    fun convertDpToPixels(dp: Int): Int {
        return dp * (resources.displayMetrics.densityDpi  / DisplayMetrics.DENSITY_DEFAULT)
    }

    fun showSnackbar(view: View, message: String, duration: Int = Snackbar.LENGTH_LONG) {
       Snackbar.make(view, message, duration).show()
    }
}
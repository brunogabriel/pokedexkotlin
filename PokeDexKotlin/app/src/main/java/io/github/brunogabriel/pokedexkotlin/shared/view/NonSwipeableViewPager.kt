package io.github.brunogabriel.pokedexkotlin.shared.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created by brunogabriel on 2019-08-30.
 */
class NonSwipeableViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {
    override fun onTouchEvent(ev: MotionEvent?) = false
    override fun onInterceptTouchEvent(ev: MotionEvent?) = false
}
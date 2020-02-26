package io.github.brunogabriel.core

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Created by bruno on 2020-02-26
 */
class NonSwipeableViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {
    override fun onTouchEvent(ev: MotionEvent?) = false
    override fun onInterceptHoverEvent(event: MotionEvent?) = false
}

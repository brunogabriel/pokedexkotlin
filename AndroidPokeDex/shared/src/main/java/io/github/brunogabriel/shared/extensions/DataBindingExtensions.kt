package io.github.brunogabriel.shared.extensions

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

inline fun <reified T : ViewDataBinding> Activity.bind(
    @LayoutRes layoutRes: Int,
    block: T.() -> Unit
): T {
    return DataBindingUtil.setContentView<T>(this, layoutRes).apply {
        block(this)
    }
}

inline fun <reified T : ViewDataBinding> LayoutInflater.bind(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup?, block: T.() -> Unit
): T {
    return DataBindingUtil.inflate<T>(this, layoutRes, parent, false).apply {
        block(this)
    }
}
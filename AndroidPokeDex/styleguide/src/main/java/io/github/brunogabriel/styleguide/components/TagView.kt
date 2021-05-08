package io.github.brunogabriel.styleguide.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.annotation.ColorInt
import androidx.databinding.DataBindingUtil
import io.github.brunogabriel.styleguide.R
import io.github.brunogabriel.styleguide.databinding.ViewTagBinding

class TagView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val binding: ViewTagBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_tag, this, true)

    fun bindTagText(text: String? = null): TagView {
        binding.tagTextView.text = text
        return this
    }

    fun setTagTextSize(size: Float): TagView {
        binding.tagTextView.textSize = size
        return this
    }

    fun setTagBackgroundColor(@ColorInt color: Int): TagView {
        binding.tagCardView.setCardBackgroundColor(color)
        return this
    }

    fun setTagTextColor(@ColorInt color: Int): TagView {
        binding.tagTextView.setTextColor(color)
        return this
    }
}
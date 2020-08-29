package com.alexluque.githubrepos.ui.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.alexluque.githubrepos.ui.common.extensions.loadImage

@BindingAdapter("doVisible")
fun View.setVisible(visible: Boolean?) {
    visibility = visible?.let {
        if (visible) View.VISIBLE else View.GONE
    } ?: View.GONE
}
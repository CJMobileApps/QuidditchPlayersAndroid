package com.cjmobileapps.quidditchplayersandroid.util.viewutil

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ViewUtil {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        Picasso.get().load(imageUrl).into(view)
    }
}

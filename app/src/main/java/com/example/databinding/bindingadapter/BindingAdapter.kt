package com.example.databinding.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic fun loadImage(imageView: ImageView, url: String) {
        Picasso.get().load(url).into(imageView)
    }
}
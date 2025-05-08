package com.app.testpexel.presentation.photo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.testpexel.utils.GlideEngine
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    // Tải ảnh
    GlideEngine.instance?.loadPhotoUrl(
        view.context,
        url.toString(),
        view
    )
}
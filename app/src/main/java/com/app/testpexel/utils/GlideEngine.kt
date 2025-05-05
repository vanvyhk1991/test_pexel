package com.app.testpexel.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class GlideEngine
private constructor() : ImageEngine {
    override fun loadPhotoUrl(context: Context, url: String, imageView: ImageView) {
        Glide.with(context).load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
    }

    override fun loadPhoto(context: Context, uri: Uri, imageView: ImageView) {
        Glide.with(context).load(uri).override(1080, 1920)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
    }

    override fun loadGifAsBitmap(context: Context, gifUri: Uri, imageView: ImageView) {
        Glide.with(context).asBitmap().load(gifUri).override(1080, 1920)
            .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView)
    }

    override fun loadGif(context: Context, gifUri: Uri, imageView: ImageView) {
        Glide.with(context).asGif().load(gifUri).override(1080, 1920)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .transition(DrawableTransitionOptions.withCrossFade()).into(imageView)
    }

    @Throws(Exception::class)
    override fun getCacheBitmap(context: Context, uri: Uri, width: Int, height: Int): Bitmap {
        return Glide.with(context).asBitmap().load(uri).override(1080, 1920)
            .diskCacheStrategy(DiskCacheStrategy.ALL).submit(width, height).get()
    }


    companion object {
        var instance: GlideEngine? = null
            get() {
                if (null == field) {
                    synchronized(GlideEngine::class.java) {
                        if (null == field) {
                            field = GlideEngine()
                        }
                    }
                }
                return field
            }
            private set
    }
}

package com.app.testpexel.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView


interface ImageEngine {
    fun loadPhotoUrl(context: Context, uri: String, imageView: ImageView)

    fun loadPhoto(context: Context, uri: Uri, imageView: ImageView)
    fun loadGifAsBitmap(context: Context, gifUri: Uri, imageView: ImageView)
    fun loadGif(context: Context, gifUri: Uri, imageView: ImageView)
    @Throws(Exception::class)
    fun getCacheBitmap(context: Context, uri: Uri, width: Int, height: Int): Bitmap?
}

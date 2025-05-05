package com.app.testpexel.data.model

import android.content.res.Resources
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * {
 *             "id": 31928142,
 *             "width": 4160,
 *             "height": 6240,
 *             "url": "https://www.pexels.com/photo/artistic-chocolate-cookie-food-photography-31928142/",
 *             "photographer": "Hilal  Bülbül",
 *             "photographer_url": "https://www.pexels.com/@hilalbulbul",
 *             "photographer_id": 35128685,
 *             "avg_color": "#7B614A",
 *             "src": {
 *                 "original": "https://images.pexels.com/photos/31928142/pexels-photo-31928142.jpeg",
 *                 "large2x": "https://images.pexels.com/photos/31928142/pexels-photo-31928142.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940",
 *                 "large": "https://images.pexels.com/photos/31928142/pexels-photo-31928142.jpeg?auto=compress&cs=tinysrgb&h=650&w=940",
 *                 "medium": "https://images.pexels.com/photos/31928142/pexels-photo-31928142.jpeg?auto=compress&cs=tinysrgb&h=350",
 *                 "small": "https://images.pexels.com/photos/31928142/pexels-photo-31928142.jpeg?auto=compress&cs=tinysrgb&h=130",
 *                 "portrait": "https://images.pexels.com/photos/31928142/pexels-photo-31928142.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=1200&w=800",
 *                 "landscape": "https://images.pexels.com/photos/31928142/pexels-photo-31928142.jpeg?auto=compress&cs=tinysrgb&fit=crop&h=627&w=1200",
 *                 "tiny": "https://images.pexels.com/photos/31928142/pexels-photo-31928142.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=200&w=280"
 *             },
 *             "liked": false,
 *             "alt": "Elegant chocolate cookies presented on a green plate with a rustic setting in natural light."
 *         }
 */
@Parcelize
data class DataPhoto(
    val id: Long,
    val width: Int,
    val height: Int,
    val url: String?,
    val photographer: String?,
    @SerializedName("photographer_url")
    val photographerUrl: String?,
    @SerializedName("photographer_id")
    val photographerId: Long,
    @SerializedName("avg_color")
    val avgColor: String?,
    val src: SrcPhoto?,
    val liked: Boolean,
    val alt: String?
): Parcelable{

    fun getSizeImage(): String{
        return "$width x $height Pixel"
    }

    fun getImagePhotoWithScreenDevice(): String{
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        return when {
            screenWidth <= 480 -> src?.small.toString()
            screenWidth <= 720 -> src?.medium.toString()
            screenWidth <= 1080 -> src?.large.toString()
            screenWidth <= 1440 -> src?.large2X.toString()
            else -> src?.original.toString()
        }
    }
}

@Parcelize
data class SrcPhoto(
    val original: String?,
    @SerializedName("large_2x")
    val large2X: String?,
    val large: String?,
    val medium: String?,
    val small: String?,
    val portrait: String?,
    val landscape: String?,
    val tiny: String?
): Parcelable
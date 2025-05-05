package com.app.testpexel.data.response

import com.app.testpexel.data.model.DataPhoto
import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val photos: List<DataPhoto>?,
    @SerializedName("total_results")
    val totalResults: Int,
)
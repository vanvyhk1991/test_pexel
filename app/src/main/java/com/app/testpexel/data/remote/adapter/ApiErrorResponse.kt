package com.app.testpexel.data.remote.adapter

import com.google.gson.annotations.SerializedName

data class ApiErrorResponse(
    val error: String? = null,
    val status: String? = null,
    val code: String? = null,
    @SerializedName("error_description")
    val errorDescription: String? = null,
    var message: String = ""
)
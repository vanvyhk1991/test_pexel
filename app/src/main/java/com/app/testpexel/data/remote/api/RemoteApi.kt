package com.app.testpexel.data.remote.api

import com.app.testpexel.data.response.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApi {
    @GET("search")
    suspend fun getVideos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PhotoResponse
}
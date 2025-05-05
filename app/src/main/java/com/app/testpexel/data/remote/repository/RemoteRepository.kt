package com.app.testpexel.data.remote.repository

import com.app.testpexel.data.remote.api.RemoteApi
import com.app.testpexel.data.response.PhotoResponse

class RemoteRepository(private val remoteApi: RemoteApi) : IRemoteRepository {
    override suspend fun getPhotos(query: String, page: Int, perPage: Int): PhotoResponse {
        return remoteApi.getVideos(query, page, perPage)
    }
}
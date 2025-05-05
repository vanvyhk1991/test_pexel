package com.app.testpexel.data.remote.repository

import com.app.testpexel.data.response.PhotoResponse


interface IRemoteRepository {
    suspend fun getPhotos(query: String, page: Int, perPage: Int): PhotoResponse
}
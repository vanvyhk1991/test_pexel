package com.app.testpexel.presentation.photo

import androidx.lifecycle.viewModelScope
import com.app.testpexel.base.BaseViewModel
import com.app.testpexel.data.model.DataPhoto
import com.app.testpexel.data.remote.repository.IRemoteRepository
import kotlinx.coroutines.launch

class PhotoViewModel(private val remoteRepository: IRemoteRepository) : BaseViewModel<MutableList<DataPhoto>>() {
    private var page: Int = 1
    private val perPage: Int = 20

    fun getVideos(query: String, loadMore: Boolean = false) {
        if (loadingI.value == true) return
        loadingI.value = true
        page = if (loadMore) page + 1 else 1
        viewModelScope.launch {
            try {
                val response = remoteRepository.getPhotos(query, page, perPage)
                if (!response.photos.isNullOrEmpty()) {
                    if (loadMore) {
                        dataI.value = (dataO.value?.plus(response.photos))?.toMutableList()
                    } else {
                        dataI.value = response.photos.toMutableList()
                    }
                } else {
                    dataI.value = mutableListOf()
                }
            } catch (e: Exception) {
                errorI.value = e
                e.printStackTrace()
            } finally {
                loadingI.value = false
            }
        }
    }
}
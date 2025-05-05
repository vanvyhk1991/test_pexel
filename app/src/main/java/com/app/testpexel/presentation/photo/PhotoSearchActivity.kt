package com.app.testpexel.presentation.photo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.testpexel.R
import com.app.testpexel.base.BaseActivity
import com.app.testpexel.databinding.ActivityPhotosBinding
import com.app.testpexel.presentation.photo_detail.PhotoDetailActivity
import com.app.testpexel.utils.gone
import com.app.testpexel.utils.handleHideKeyBoardWhenClick
import com.app.testpexel.utils.hideSoftKeyboard
import com.app.testpexel.utils.viewBindings
import com.app.testpexel.utils.visible
import org.koin.android.ext.android.inject

class PhotoSearchActivity : BaseActivity<ActivityPhotosBinding>(R.layout.activity_photos) {
    companion object {
        const val DEFAULT_QUERY = "people"
    }

    private val viewModel: PhotoViewModel by inject<PhotoViewModel>()
    private val photoAdapter: PhotoAdapter by lazy { PhotoAdapter() }
    private var isLoadingMore: Boolean = false
    private var query: String = DEFAULT_QUERY

    override val binding: ActivityPhotosBinding by viewBindings(ActivityPhotosBinding::bind)

    override fun setupViews() {
        injectAdapter()
        viewModel.getVideos(query, isLoadingMore)
        setEvent()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun bindViewModel() {
        viewModel.loadingO.observe(this) {
            binding.prgLoading.visibility = if(it) View.VISIBLE else View.GONE
        }
        viewModel.dataO.observe(this) {
            binding.prgLoadmore.gone()
            isLoadingMore = false
            photoAdapter.submitList(it)
            photoAdapter.notifyDataSetChanged()
            binding.swRefresh.isRefreshing = false
        }
    }

    private fun injectAdapter() {
        binding.apply {
            val linearLayoutManager = LinearLayoutManager(this@PhotoSearchActivity)
            rvPhotos.layoutManager = linearLayoutManager
            rvPhotos.adapter = photoAdapter
            photoAdapter.onItemPhotoClickListener = {
                Intent(this@PhotoSearchActivity, PhotoDetailActivity::class.java).apply {
                    putExtra(PhotoDetailActivity.DATA_PHOTO, it)
                    startActivity(this)
                }
            }
            photoAdapter.submitList(emptyList())
        }
    }

    private fun setEvent() {
        binding.apply {
            rvPhotos.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    val isLastItemVisible =
                        (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    val shouldLoadMore = isLastItemVisible && firstVisibleItemPosition >= 5

                    if (shouldLoadMore && !isLoadingMore) {
                        isLoadingMore = true
                        loadMoreData()
                    }
                }
            })

            swRefresh.setOnRefreshListener {
                swRefresh.isRefreshing = true
                viewModel.getVideos(query = query, loadMore = false)
            }

            edtSearch.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    query = edtSearch.getText().toString().ifEmpty { DEFAULT_QUERY }
                    viewModel.getVideos(query = query, loadMore = false)
                    hideSoftKeyboard(this@PhotoSearchActivity)
                    edtSearch.clearFocus()
                    true
                }
                false
            }
        }
    }

    private fun loadMoreData() {
        binding.apply {
            prgLoadmore.visible()
            viewModel.getVideos(query = query, loadMore = true)
        }
    }
}

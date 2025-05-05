package com.app.testpexel.presentation.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.testpexel.data.model.DataPhoto
import com.app.testpexel.databinding.ItemListPhotoBinding
import com.app.testpexel.utils.GlideEngine

class PhotoAdapter() : ListAdapter<DataPhoto, PhotoAdapter.ItemPhotoViewHolder>(object :
    DiffUtil.ItemCallback<DataPhoto>() {
    override fun areItemsTheSame(oldItem: DataPhoto, newItem: DataPhoto): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.photographer == newItem.photographer &&
                oldItem.photographerId == newItem.photographerId &&
                oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: DataPhoto, newItem: DataPhoto): Boolean {
        return oldItem == newItem
    }
}) {

    var onItemPhotoClickListener: ((dataPhoto: DataPhoto?) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ItemPhotoViewHolder(ItemListPhotoBinding.inflate(layoutInflater, parent, false))

    }

    override fun onBindViewHolder(holder: ItemPhotoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ItemPhotoViewHolder(private val binding: ItemListPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(itemPhoto: DataPhoto) {
            binding.apply {
                GlideEngine.instance?.loadPhotoUrl(
                    root.context, itemPhoto.src?.original.toString(), ivThumbnail
                )
                tvDescription.text = itemPhoto.alt
                tvOwner.text = itemPhoto.photographer
                tvSizeImage.text = itemPhoto.getSizeImage()

                root.setOnClickListener {
                    onItemPhotoClickListener?.invoke(itemPhoto)
                }
            }
        }
    }
}
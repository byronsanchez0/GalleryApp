package com.example.galleryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.galleryapp.databinding.ItemPhoto2Binding
import com.example.galleryapp.databinding.ItemPhotoBinding
import java.io.File
import java.lang.IllegalArgumentException

class PhotosAdapter(
    private var photoList: List<File>,
    private val selectionChangeListener: SelectionChangeListener,
) : RecyclerView.Adapter<ViewHolder>() {

    private val isSelected = MutableList(photoList.size) { false }

    inner class FirstViewHolder(binding: ItemPhotoBinding) : ViewHolder(binding.root) {
        val image = binding.itemImg
        val icon = binding.iconSelected
    }

    inner class SecondViewHolder(binding: ItemPhoto2Binding) :
        ViewHolder(binding.root) {
        val image = binding.itemImg2
        val icon = binding.iconSelected
    }

    interface SelectionChangeListener {
        fun onSelectionChanged(selectedCount: Int)
    }

    fun getSelectedImages(): List<File> {
        return photoList.filterIndexed { index, _ -> isSelected[index] }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ONE -> {
                val binding = ItemPhotoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                FirstViewHolder(binding)
            }

            VIEW_TYPE_TWO -> {
                val binding = ItemPhoto2Binding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                SecondViewHolder(binding)
            }

            else -> throw IllegalArgumentException()

        }

    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_ONE -> {
                val firstViewHolder = holder as FirstViewHolder
                Glide.with(holder.image.context)
                    .load(photoList[position])
                    .into(firstViewHolder.image)

                firstViewHolder.image.setOnClickListener {
                    isSelected[position] = !isSelected[position]
                    firstViewHolder.image.alpha = if (isSelected[position]) 0.5f else 1.0f
                    firstViewHolder.icon.visibility =
                        if (isSelected[position]) View.VISIBLE else View.GONE
                    selectionChangeListener.onSelectionChanged(getSelectedImages().size)
                }
            }

            VIEW_TYPE_TWO -> {
                val secondViewHolder = holder as SecondViewHolder
                Glide.with(holder.image.context)
                    .load(photoList[position])
                    .into(secondViewHolder.image)
//
                secondViewHolder.image.setOnClickListener {
                    isSelected[position] = !isSelected[position]
                    secondViewHolder.image.alpha = if (isSelected[position]) 0.5f else 1.0f
                    secondViewHolder.icon.visibility =
                        if (isSelected[position]) View.VISIBLE else View.GONE
                    selectionChangeListener.onSelectionChanged(getSelectedImages().size)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 3 == 0) {
            VIEW_TYPE_ONE
        } else {
            VIEW_TYPE_TWO
        }
    }

    companion object {
        private const val VIEW_TYPE_ONE = 1
        private const val VIEW_TYPE_TWO = 2
    }
}



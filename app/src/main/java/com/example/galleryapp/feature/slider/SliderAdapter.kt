package com.example.galleryapp.feature.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.galleryapp.databinding.SelecteditemBinding
import java.io.File

class SliderAdapter(private val context: Context, private var selectedPhotos: List<File>) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(binding: SelecteditemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image = binding.selectedPhoto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = SelecteditemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SliderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return selectedPhotos.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        Glide.with(context).load(selectedPhotos[position]).into(holder.image)
    }
}
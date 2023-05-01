package com.example.galleryapp.feature.slider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentSliderBinding
import java.io.File


class SliderFragment(private val selectedPhotos: List<File>) : Fragment(R.layout.fragment_slider) {

    private lateinit var binding: FragmentSliderBinding
    private val snapHelper = LinearSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSliderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.recyclerView
        with(binding.recyclerView) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = SliderAdapter(requireContext(), selectedPhotos)
        }
        snapHelper.attachToRecyclerView(recyclerView)
    }

}
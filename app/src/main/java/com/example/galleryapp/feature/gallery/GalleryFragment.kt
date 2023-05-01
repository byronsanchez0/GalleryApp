package com.example.galleryapp.feature.gallery

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.PhotosAdapter
import com.example.galleryapp.R
import com.example.galleryapp.feature.slider.SliderFragment
import com.example.galleryapp.databinding.FragmentGalleryBinding
import java.io.File


class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    private lateinit var _binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photosFolder = File(Environment.getExternalStorageDirectory(), camera_folder)
        val photosFiles =
            photosFolder.listFiles()?.filter { it.extension == image_extension }?.toList()
                ?: emptyList<File?>().reversed()

        val galleryAdapter =
            PhotosAdapter(photosFiles, object : PhotosAdapter.SelectionChangeListener {
                override fun onSelectionChanged(selectedCount: Int) {
                    _binding.btnShowSlider.visibility =
                        if (selectedCount > 0) View.VISIBLE else View.GONE
                }
            })
        with(_binding.recyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 2).also {
                it.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (position % 3 == 0)
                            2
                        else
                            1
                    }
                }
            }
            adapter = galleryAdapter
        }
        _binding.takeAPhotoButton.setOnClickListener {
            findNavController().navigate(R.id.action_galleryFragment2_to_cameraFragment2)

        }
        _binding.btnShowSlider.setOnClickListener {
            navigatToSliderFragment(galleryAdapter.getSelectedImages())
        }
        _binding.btnShowSlider.visibility = View.GONE

    }

    private fun navigatToSliderFragment(selectedPhotos: List<File>) {
        val sliderFragment = SliderFragment(selectedPhotos)
        parentFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, sliderFragment)
            .addToBackStack(null).commit()

    }

    companion object {
        const val camera_folder: String = "Pictures/CameraX-Image"
        const val image_extension: String = "jpg"
    }
}
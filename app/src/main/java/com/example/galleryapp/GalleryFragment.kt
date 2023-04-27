package com.example.galleryapp

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.galleryapp.databinding.FragmentCameraBinding
import com.example.galleryapp.databinding.FragmentGalleryBinding
import java.io.File


class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val photosFolder = File(Environment.getExternalStorageDirectory(), camera_folder)
        val photosFiles = photosFolder.listFiles()?.filter { it.extension == image_extension }?.toList()?: emptyList()

        val galleryPhotosAdapter = Photos_Adapter( photosFiles)
        binding.recyclerView.adapter = galleryPhotosAdapter


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.takeAPhotoButton.setOnClickListener{
            findNavController().navigate(R.id.action_galleryFragment2_to_cameraFragment2)
        }
    }

    companion object {
        const val camera_folder: String = "Images/CameraX-Image"
        const val image_extension:     String = ".jpeg"
    }
}
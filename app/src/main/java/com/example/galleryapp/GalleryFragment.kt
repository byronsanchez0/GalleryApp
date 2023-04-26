package com.example.galleryapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.galleryapp.databinding.FragmentCameraBinding
import com.example.galleryapp.databinding.FragmentGalleryBinding


class GalleryFragment : Fragment(R.layout.fragment_gallery) {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.takeAPhotoButton.setOnClickListener{
            //findNavController().navigate(R.id.action_galleryFragment2_to_cameraFragment2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)

        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            GalleryFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
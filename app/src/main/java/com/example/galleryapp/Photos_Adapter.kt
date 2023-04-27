package com.example.galleryapp

import android.content.Context
import android.os.Environment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.databinding.ItemPhotoBinding
import java.io.File

//AQUI TENES QUE PASAR LS LISTA EN RECYCLER VIEW ESTABA PROBANDO Y NO FURULA NO MUESTRA NOTHING
class Photos_Adapter( private var list: List<File>): RecyclerView.Adapter<Photos_Adapter.PhotosViewHolder>(){
    class PhotosViewHolder(binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root){
        val view = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context))
        return PhotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
//        var gpath: String = Environment.getExternalStorageDirectory().absolutePath
//        var spath = "Pictures/CameraX-Image"
//        var fullpath = File(gpath + File.separator + spath)
//         list = imageReader(fullpath)
    }

//    private fun imageReader(root: File): ArrayList<File> {
////        val fileList: ArrayList<File> = ArrayList()
////        val listAllphotos = root.listFiles()
////
////        if(listAllphotos!= null && listAllphotos.size > 0) {
////            for(currentFile in listAllphotos) {
////                if(currentFile.name.endsWith(".jpeg")){
////                    fileList.add(currentFile.absoluteFile)
////                }
////            }
////        }
////        return fileList
//    }

    override fun getItemCount(): Int {
        return list.size
    }




}
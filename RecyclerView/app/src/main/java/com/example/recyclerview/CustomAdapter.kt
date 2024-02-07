package com.example.recyclerview


import PhotoModelDiffCallback
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private var photoList: List<PhotoModel>) :
    RecyclerView.Adapter<CustomAdapter.PhotoViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomAdapter.PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_design, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomAdapter.PhotoViewHolder, position: Int) {
        val ItemsViewModel = photoList[position]

        holder.imageView.setImageResource(ItemsViewModel.image)
        holder.textView.text = ItemsViewModel.text
    }

    fun updatePhotoList(oldList: List<PhotoModel>, newList: List<PhotoModel>) {
        val diffResult = DiffUtil.calculateDiff(PhotoModelDiffCallback(oldList, newList))

        Log.d("CustomAdapter", "updatePhotoList: ${photoList.size}")
        diffResult.dispatchUpdatesTo(this)
    }
    override fun getItemCount(): Int {
        return photoList.size
    }

    class PhotoViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

}
package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    var photoList = ArrayList<PhotoModel>()
    var oldPhotoList = photoList.toList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fabDelete = findViewById<FloatingActionButton>(R.id.fab_delete)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fab_add)
        recyclerView.layoutManager = LinearLayoutManager(this)
        var adapter = CustomAdapter(photoList)
        recyclerView.adapter = adapter

        fabDelete.setOnClickListener {
            if (photoList.isNotEmpty()) {

                val removedItem = photoList.removeAt(photoList.size - 1)
                //adapter.notifyItemRemoved(photoList.size)
                adapter.updatePhotoList(oldPhotoList, photoList)
                oldPhotoList = photoList.toList()

            } else {
                Snackbar.make(it, "No items to remove", Snackbar.LENGTH_SHORT).show()
            }
        }
        fabAdd.setOnClickListener {
            val newItemName = "Item ${photoList.size + 1}"
            val newItem = PhotoModel(R.drawable.ic_launcher_background, newItemName)
            photoList.add(newItem)
            Log.d("MainActivity", "List size before update: ${photoList.size}")
            adapter.updatePhotoList(oldPhotoList, photoList)
            Log.d("MainActivity", "List size after update: ${photoList.size}")
            oldPhotoList = photoList.toList()
        }


    }
}
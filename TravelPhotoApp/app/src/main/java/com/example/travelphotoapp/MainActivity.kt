package com.example.travelphotoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    var currentImage = 0
    lateinit var image: ImageView
    var places = listOf<String>("Gateway of India", "Jimmy's Restaurant", "Bandra-Worli SeaLink", "Colaba", "Kanheri Caves")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.pic0)

        val buttonPrev = findViewById<ImageButton>(R.id.btnPrevious)
        val buttonNext = findViewById<ImageButton>(R.id.btnNext)
        val locationName = findViewById<TextView>(R.id.tvName)

        fun getCurrentImage() {
            // to get the id of the current image e.g. pic0
            var idCurrentImageString = "pic$currentImage"

            //to get numberAddress
            var idCurrentImageInt = this.resources.getIdentifier(
                idCurrentImageString,
                "id",
                packageName
            )
            image = findViewById(idCurrentImageInt)
            locationName.text = places[currentImage]
        }

        buttonPrev.setOnClickListener {
            getCurrentImage()
            image.alpha = 0f
            currentImage = (5 + currentImage - 1) % 5
            getCurrentImage()
            image.alpha = 1f


        }
        buttonNext.setOnClickListener {
            getCurrentImage()
            image.alpha = 0f
            currentImage = (5 + currentImage + 1) % 5
            getCurrentImage()
            image.alpha = 1f
        }
    }
}
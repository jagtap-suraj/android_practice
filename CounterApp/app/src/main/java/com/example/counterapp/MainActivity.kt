package com.example.counterapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var counterButton: Button
    lateinit var counterTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        counterButton = findViewById(R.id.btnIncrement)
        counterTextView = findViewById(R.id.tvCounter)

        counterButton.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        if(v.id == R.id.btnIncrement) {
            var count = counterTextView.text.toString().toInt()
            count++
            counterTextView.text = count.toString()
        }
    }
}
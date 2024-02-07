package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create an instance of the first fragment & second fragment
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()
        val buttonFragment1 = findViewById<Button>(R.id.btnfragment1)
        val buttonFragment2 = findViewById<Button>(R.id.btnfragment2)


        // Add the first fragment to the fragment container
        replaceFragment(firstFragment)

        // Set a click listener for the button to replace the fragment
        buttonFragment1.setOnClickListener { replaceFragment(firstFragment) }
        buttonFragment2.setOnClickListener { replaceFragment(secondFragment) }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            addToBackStack(null)
            commit()
        }
    }
}
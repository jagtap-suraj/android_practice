package com.example.surajportfolio

import android.content.Intent
import android.net.Uri
import java.util.concurrent.Executors
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageButton
import com.google.android.material.snackbar.Snackbar
import java.io.InputStream
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import androidx.appcompat.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable



class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonResume = findViewById<Button>(R.id.btnResume)
        val buttonProjects = findViewById<Button>(R.id.btnPortfolio)
        val imageButtonPhone = findViewById<ImageButton>(R.id.ibPhone)
        val imageButtonMail = findViewById<ImageButton>(R.id.ibMail)
        val imageButtonLinkedIn = findViewById<ImageButton>(R.id.ibLinkedIn)
        val imageButtonGitHub = findViewById<ImageButton>(R.id.ibGitHub)


        imageButtonPhone.setOnClickListener {
            // open dialer on click
            intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:9969518413")
            startActivity(intent)
        }

        imageButtonLinkedIn.setOnClickListener {
            // open linkedin url in browser on click
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.linkedin.com/in/jagtap-suraj")
            startActivity(intent)
        }

        imageButtonGitHub.setOnClickListener {
            // open github url in browser on click
            intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.github.com/jagtap-suraj")
            startActivity(intent)
        }

        imageButtonMail.setOnClickListener {
            // open mail app on click
            intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:jagtap-suraj@outlook.com")
            startActivity(intent)
        }

        buttonResume.setOnClickListener {
            val intent = Intent(this@MainActivity, ResumeActivity::class.java)
            startActivity(intent)
        }


        buttonProjects.setOnClickListener {
            val dialog = AlertDialog.Builder(this).apply {
                setView(R.layout.custom_dialog_layout)
            }.create()

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }


    }

}
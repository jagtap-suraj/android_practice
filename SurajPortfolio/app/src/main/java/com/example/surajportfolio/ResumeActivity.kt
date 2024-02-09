package com.example.surajportfolio

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class ResumeActivity: AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_resume)

        webView = findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled = true
        val pdfUrl = "https://drive.google.com/file/d/1rIapZSj2XwZutlJgyr3vv6vO1EfPIgde/view"

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Page loading finished
            }
        }

        webView.loadUrl(pdfUrl)
    }
}

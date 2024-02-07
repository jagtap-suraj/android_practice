package com.example.objectbox

import android.content.Context
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

object MyObjectBox {
    lateinit var boxStore: BoxStore
        private set

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()

        // Enable Android ObjectBrowser
        if (BuildConfig.DEBUG) {
            AndroidObjectBrowser(boxStore).start(context.applicationContext)
        }
    }
}

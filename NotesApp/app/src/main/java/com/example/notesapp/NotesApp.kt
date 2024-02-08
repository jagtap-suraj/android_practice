package com.example.notesapp

import android.app.Application
import android.util.Log
import io.objectbox.BoxStore
import io.objectbox.android.Admin
import io.objectbox.android.AndroidObjectBrowser


class NotesApp : Application() {

    lateinit var boxStore: BoxStore
        private set

    override fun onCreate() {
        super.onCreate()
        initializeObjectBox()
    }

    private fun initializeObjectBox() {
        boxStore = MyObjectBox.builder().androidContext(this).build()
        val started = Admin(boxStore).start(this)
        Log.i("ObjectBrowser", "Started: $started")
    }
}

package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var saveButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotesAdapter
    private lateinit var notesDao: NotesDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize ObjectBox
        val boxStore = (application as NotesApp).boxStore
        notesDao = NotesDao(boxStore)

        // Initialize views
        editText = findViewById(R.id.editText)
        saveButton = findViewById(R.id.saveButton)
        recyclerView = findViewById(R.id.recyclerView)

        // Setup RecyclerView
        adapter = NotesAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Load notes from the database
        loadNotes()

        // Save button click listener
        saveButton.setOnClickListener {
            val noteText = editText.text.toString().trim()
            if (noteText.isNotEmpty()) {
                saveNoteToDatabase(noteText)
                editText.text.clear()
            }
        }
    }

    private fun saveNoteToDatabase(noteText: String) {
        val note = Note(text = noteText)
        notesDao.insert(note)
        loadNotes()
    }

    private fun loadNotes() {
        val notes = notesDao.getAllNotes()
        adapter.submitList(notes)
    }
}
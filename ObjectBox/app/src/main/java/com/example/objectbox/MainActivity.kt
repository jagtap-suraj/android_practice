package com.example.objectbox

import NoteAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.objectbox.Box

class MainActivity : AppCompatActivity() {
    private lateinit var noteBox: Box<Note>
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSave = findViewById<Button>(R.id.btnSave)
        val etNote = findViewById<EditText>(R.id.etNote)
        val recyclerViewNotes = findViewById<RecyclerView>(R.id.recyclerViewNotes)

        noteBox = (application as App).boxStore.boxFor(Note::class.java)

        // Set up RecyclerView
        noteAdapter = NoteAdapter(noteBox.all)
        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        recyclerViewNotes.adapter = noteAdapter

        btnSave.setOnClickListener {
            val text = etNote.text.toString().trim()
            if (text.isNotEmpty()) {
                val note = Note(text = text, timestamp = System.currentTimeMillis())
                noteBox.put(note)
                etNote.text.clear()

                // Update RecyclerView
                noteAdapter.notes = noteBox.all
                noteAdapter.notifyDataSetChanged()
            }
        }
    }
}

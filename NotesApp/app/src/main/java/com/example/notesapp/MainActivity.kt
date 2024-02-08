package com.example.notesapp

//import NotesAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import android.text.Editable
import android.text.TextWatcher

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNote: EditText
    private lateinit var editTextType: EditText
    private lateinit var saveButton: Button
    private lateinit var searchButton: Button

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
        editTextNote = findViewById(R.id.etNote)
        editTextType = findViewById(R.id.etType)

        saveButton = findViewById(R.id.btnSave)
        searchButton = findViewById(R.id.btnSearch)

        recyclerView = findViewById(R.id.recyclerView)

        adapter = NotesAdapter(
            deleteNote = { note -> deleteNote(note) },
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Load notes from the database
        lifecycleScope.launch {
            loadNotes()
        }

        // Save button click listener
        saveButton.setOnClickListener {
            val noteText = editTextNote.text.toString()
            val noteType = editTextType.text.toString()
            if (noteText.isNotEmpty() && noteType.isNotEmpty()) {
                lifecycleScope.launch {
                    saveNoteToDatabase(noteText, noteType)
                    editTextNote.text.clear()
                    editTextType.text.clear()
                }
            }
        }

        searchButton.setOnClickListener {
            val noteText = editTextNote.text.toString()
            val noteType = editTextType.text.toString()
            if (noteText.isNotEmpty() || noteType.isNotEmpty()) {
                lifecycleScope.launch {
                    searchNotes(noteText, noteType)
                }
            }
        }

        // Text change listener for both editTextNote and editTextType
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                val noteText = editTextNote.text.toString()
                val noteType = editTextType.text.toString()
                if (noteText.isEmpty() && noteType.isEmpty()) {
                    lifecycleScope.launch {
                        loadNotes()
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}



        }

        editTextNote.addTextChangedListener(textWatcher)
        editTextType.addTextChangedListener(textWatcher)

    }

    private suspend fun saveNoteToDatabase(noteText: String, noteType: String) {
        val note = Note(text = noteText, type = noteType)
        notesDao.insert(note)
        loadNotes()
    }

    private suspend fun loadNotes() {
        val notes = notesDao.getAllNotes()
        adapter.submitList(notes)
    }

    private suspend fun searchNotes(noteText: String, noteType: String) {
        val notes = notesDao.searchNotes(noteText, noteType)
        adapter.submitList(notes)
    }

    private fun deleteNote(note: Note) {
        lifecycleScope.launch {
            notesDao.delete(note)
            loadNotes()
        }
    }

}
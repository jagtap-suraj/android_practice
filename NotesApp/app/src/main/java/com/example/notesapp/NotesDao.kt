package com.example.notesapp

import io.objectbox.Box
import io.objectbox.BoxStore

class NotesDao(private val notesBox: BoxStore) {

    fun insert(note: Note) {
        notesBox.boxFor(Note::class.java).put(note)
    }

    fun getAllNotes(): List<Note> {
        return notesBox.boxFor(Note::class.java).all
    }
}

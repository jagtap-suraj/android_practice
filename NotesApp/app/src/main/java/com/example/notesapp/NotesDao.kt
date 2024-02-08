package com.example.notesapp

import io.objectbox.Box
import io.objectbox.BoxStore

class NotesDao(private val boxStore: BoxStore) {

    val notesBox = boxStore.boxFor(Note::class.java)

    fun insert(note: Note): Long {
       return notesBox.put(note)
    }

    fun getAllNotes(): List<Note> {
        return notesBox.all
    }
}

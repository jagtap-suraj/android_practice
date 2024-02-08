package com.example.notesapp

import android.util.Log
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.query.QueryBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotesDao(private val boxStore: BoxStore) {

    private val notesBox = boxStore.boxFor(Note::class.java)

//    fun insert(note: Note): Long {
//       return notesBox.put(note)
//    }

    suspend fun insert(note: Note) {
        Log.d("Coroutine", "Inserting note: $note")
        withContext(Dispatchers.IO) {
            notesBox.put(note)
        }
        Log.d("Coroutine", "Note inserted successfully")
        //print all notes
        val notes = notesBox.all
        for (note in notes) {
            Log.d("Coroutine", "Note: $note")
        }
        //print size of notes
        Log.d("Coroutine", "Size of notes: ${notes.size}")
        for (note in notes) {
            Log.d("Coroutine", "Note2: $note")
        }
    }

    suspend fun delete(note: Note) {
        withContext(Dispatchers.IO) {
            notesBox.remove(note)
        }
    }


    suspend fun searchNotes(noteText: String, noteType: String): List<Note> {
        return withContext(Dispatchers.IO) {
            notesBox.query()
                .contains(Note_.text, noteText, QueryBuilder.StringOrder.CASE_INSENSITIVE)
                .contains(Note_.type, noteType, QueryBuilder.StringOrder.CASE_INSENSITIVE)
                .build()
                .find()
        }
    }


    // Sort
    suspend fun sortNotes(): List<Note> {
        return withContext(Dispatchers.IO) {
            notesBox.query().order(Note_.text).build().find()
        }
    }


    suspend fun getAllNotes(): List<Note> {
        return withContext(Dispatchers.IO) {
            notesBox.all
        }
    }
}

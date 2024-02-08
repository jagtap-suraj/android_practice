package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(
    private val deleteNote: (Note) -> Unit,
) : ListAdapter<Note, NotesAdapter.NoteViewHolder>(DiffCallback()){

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val noteTextView: TextView = itemView.findViewById(R.id.noteTextView)
        private val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.btnDelete)

        fun bind(note: Note, deleteNote: (Note) -> Unit){
            noteTextView.text = note.text
            typeTextView.text = note.type
            deleteButton.setOnClickListener {
                deleteNote.invoke(note)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = getItem(position)
        holder.bind(currentNote, deleteNote)
    }

    class DiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}

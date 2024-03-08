package com.example.contactsnotes.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsnotes.R
import com.example.contactsnotes.data.model.ContactNote
import kotlinx.android.synthetic.main.item_contact_note.view.*

class ContactAdapter(
    var notes: MutableList<ContactNote>,
    val editClickListener: (ContactNote) -> Unit,
    val deleteClickListener: (ContactNote) -> Unit
) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contact_note, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: ContactNote) {
            itemView.noteTextView.text = note.note
            itemView.editButton.setOnClickListener { editClickListener(note) }
            itemView.deleteButton.setOnClickListener { deleteClickListener(note) }
        }
    }
}

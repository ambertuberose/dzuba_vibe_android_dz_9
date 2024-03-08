package com.example.contactsnotes.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactsnotes.R
import com.example.contactsnotes.data.model.ContactNote
import com.example.contactsnotes.ui.adapter.ContactAdapter
import com.example.contactsnotes.ui.viewmodel.ContactViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.app.AlertDialog
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ContactViewModel
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ContactViewModel::class.java)

        setupRecyclerView()

        viewModel.contacts.observe(this, { notes ->
            adapter.notes = notes.toMutableList()
            adapter.notifyDataSetChanged()
        })
    }

    private fun setupRecyclerView() {
        adapter = ContactAdapter(
            mutableListOf(),
            editClickListener = { note -> editNoteDialog(note) },
            deleteClickListener = { note -> viewModel.deleteContactNote(note.id) }
        )

        contactRecyclerView.layoutManager = LinearLayoutManager(this)
        contactRecyclerView.adapter = adapter
    }

    private fun editNoteDialog(note: ContactNote) {
        val builder = AlertDialog.Builder(this)
        val editText = EditText(this)
        editText.setText(note.note)

        builder.setTitle("Edit Note")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                val updatedNote = editText.text.toString()
                viewModel.updateContactNote(note.id, updatedNote)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

}

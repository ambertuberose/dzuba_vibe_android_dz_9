// File: ContactViewModel.kt
package com.example.contactsnotes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.contactsnotes.data.model.ContactNote
import com.example.contactsnotes.data.repository.ContactNoteRepository
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactNoteRepository) : ViewModel() {

    val contacts = repository.getNotesForContact(contactId = 0).asLiveData()

    fun addContactNote(note: String) {
        viewModelScope.launch {
            repository.insert(ContactNote(contactId = 0, note = note))
        }
    }

    fun deleteContactNote(noteId: Long) {
        viewModelScope.launch {
            repository.delete(noteId)
        }
    }

    fun editContactNote(noteId: Long, newNote: String) {
        viewModelScope.launch {
            val note = repository.getNoteById(noteId)
            if (note != null) {
                repository.update(note.copy(note = newNote))
            }
        }
    }

    fun updateContactNote(noteId: Long, newNote: String) {
        viewModelScope.launch {
            val note = repository.getNoteById(noteId)
            if (note != null) {
                repository.update(note.copy(note = newNote))
            }
        }
    }

}

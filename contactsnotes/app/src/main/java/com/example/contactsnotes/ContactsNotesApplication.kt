package com.example.contactsnotes

import android.app.Application
import com.example.contactsnotes.data.database.ContactNoteDatabase
import com.example.contactsnotes.data.repository.ContactNoteRepository
import com.example.contactsnotes.ui.viewmodel.ContactViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import android.content.ContentResolver

class ContactsNotesApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { ContactNoteDatabase.getDatabase(this, applicationScope) }
    private val contentResolver by lazy { applicationContext.contentResolver }
    private val repository by lazy { ContactNoteRepository(database.contactNoteDao(), contentResolver) }
    val viewModel by lazy { ContactViewModel(repository) }
}

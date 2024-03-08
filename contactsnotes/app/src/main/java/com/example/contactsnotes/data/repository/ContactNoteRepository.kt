package com.example.contactsnotes.data.repository

import android.provider.ContactsContract
import com.example.contactsnotes.data.database.ContactNoteDao
import com.example.contactsnotes.data.model.ContactNote
import kotlinx.coroutines.flow.Flow
import android.content.ContentResolver
import android.util.Log


class ContactNoteRepository(
    private val contactNoteDao: ContactNoteDao,
    private val contentResolver: ContentResolver
) {
    fun getContactsFromContentProvider(): List<String> {
        val contacts = mutableListOf<String>()
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        cursor?.use {
            val nameIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
            if (nameIndex != -1) {
                while (it.moveToNext()) {
                    val name = it.getString(nameIndex)
                    contacts.add(name)
                }
            } else {
                // Столбец не найден, выполняем обработку ошибки
                // Например, выводим сообщение об ошибке или записываем лог
                Log.e("ContactNoteRepository", "Column DISPLAY_NAME not found")
            }
        }
        return contacts
    }

    fun getNotesForContact(contactId: Long): Flow<List<ContactNote>> {
        return contactNoteDao.getNotesForContact(contactId)
    }

    fun getNoteById(noteId: Long): Flow<ContactNote?> {
        return contactNoteDao.getNoteById(noteId)
    }

    suspend fun insert(note: ContactNote) {
        contactNoteDao.insert(note)
    }

    suspend fun delete(noteId: Long) {
        contactNoteDao.delete(noteId)
    }

    suspend fun update(contactNote: ContactNote) {
        contactNoteDao.update(contactNote)
    }
}

// File: ContactNoteDao.kt
package com.example.contactsnotes.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.contactsnotes.data.model.ContactNote
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactNoteDao {
    @Query("SELECT * FROM contact_notes WHERE contactId = :contactId")
    fun getNotesForContact(contactId: Long): Flow<List<ContactNote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: ContactNote)

    @Query("DELETE FROM contact_notes WHERE id = :noteId")
    suspend fun delete(noteId: Long)

    @Update
    suspend fun update(note: ContactNote)

    @Query("SELECT * FROM contact_notes WHERE id = :noteId")
    fun getNoteById(noteId: Long): Flow<ContactNote?>
}

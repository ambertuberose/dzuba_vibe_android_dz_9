package com.example.contactsnotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contactsnotes.data.model.ContactNote
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ContactNote::class], version = 1)
abstract class ContactNoteDatabase : RoomDatabase() {
    abstract fun contactNoteDao(): ContactNoteDao

    companion object {
        @Volatile
        private var INSTANCE: ContactNoteDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ContactNoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactNoteDatabase::class.java,
                    "contact_note_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

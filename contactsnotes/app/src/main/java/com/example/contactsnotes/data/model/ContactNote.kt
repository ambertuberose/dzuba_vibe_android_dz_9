package com.example.contactsnotes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_notes")
data class ContactNote(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val contactId: Long,
    val note: String
)

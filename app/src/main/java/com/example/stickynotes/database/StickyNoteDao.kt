package com.example.stickynotes.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.stickynotes.utils.StickyNoteData

@Dao
interface StickyNoteDao {

    @Upsert
    suspend fun upsertStickyNote(note: StickyNoteData) : Long

    @Delete
    suspend fun deleteStickyNote(note: StickyNoteData)

    @Query("SELECT * FROM stickynote")
    suspend fun getStickyNotes(): List<StickyNoteData>
}
package com.example.stickynotes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface StickyNoteDao {

    @Upsert
    suspend fun upsertStickyNote(note: StickyNoteData)

    @Delete
    suspend fun deleteStickyNote(note: StickyNoteData)

    @Query("SELECT * FROM stickynote")
    suspend fun getStickyNotes(): List<StickyNoteData>
}
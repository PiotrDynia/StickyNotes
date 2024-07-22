package com.example.stickynotes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stickynotes.utils.StickyNoteData

@Database(
    entities = [StickyNoteData::class],
    version = 1
)
abstract class StickyNoteDatabase: RoomDatabase() {
    abstract fun dao(): StickyNoteDao
}
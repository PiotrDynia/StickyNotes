package com.example.stickynotes

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [StickyNoteData::class],
    version = 1
)
abstract class StickyNoteDatabase: RoomDatabase() {
    abstract fun dao(): StickyNoteDao
}
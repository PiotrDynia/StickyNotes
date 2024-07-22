package com.example.stickynotes

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.stickynotes.database.StickyNoteDao
import com.example.stickynotes.database.StickyNoteDatabase
import com.example.stickynotes.utils.StickyNoteData
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
@LargeTest
class DatabaseTests {
    private lateinit var stickyNoteDao: StickyNoteDao
    private lateinit var db: StickyNoteDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, StickyNoteDatabase::class.java).build()
        stickyNoteDao = db.dao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun databaseTest_correctlyWritesAndReadsRecords() = runBlocking {
        val stickyNote = StickyNoteData(
            color = Color.Blue.toArgb(),
            title = "Test title",
            description = "Test description",
            visible = true
        )
        val newId = stickyNoteDao.upsertStickyNote(stickyNote)
        val notes = stickyNoteDao.getStickyNotes()
        val expectedStickyNote = stickyNote.copy(id = newId.toInt())

        assert(notes.contains(expectedStickyNote)) { "Notes do not contain the expected sticky note" }
    }

    @Test
    fun databaseTest_correctlyDeletesRecord() = runBlocking {
        val stickyNote = StickyNoteData(
            color = Color.Blue.toArgb(),
            title = "Test title",
            description = "Test description",
            visible = true
        )
        val newId = stickyNoteDao.upsertStickyNote(stickyNote)
        val insertedStickyNote = stickyNote.copy(id = newId.toInt())
        stickyNoteDao.deleteStickyNote(insertedStickyNote)
        val notes = stickyNoteDao.getStickyNotes()

        assert(!notes.contains(insertedStickyNote)) { "Note wasn't deleted" }
    }
}
package com.example.stickynotes

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.stickynotes.database.StickyNoteDao
import com.example.stickynotes.database.StickyNoteDatabase
import com.example.stickynotes.state.StickyNoteAction
import com.example.stickynotes.state.StickyNoteViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.Assert.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Before
import java.io.IOException

class StickyNoteViewModelTests {
    private lateinit var stickyNoteDao: StickyNoteDao
    private lateinit var db: StickyNoteDatabase
    private lateinit var viewModel: StickyNoteViewModel

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, StickyNoteDatabase::class.java).build()
        stickyNoteDao = db.dao()
        viewModel = StickyNoteViewModel(stickyNoteDao)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun stickyNoteViewModelTest_correctlyCreatesNote() {
        viewModel.onAction(StickyNoteAction.AddNote)
        val state = viewModel.state.value
        assertTrue(state.notes.isNotEmpty())
    }

    @Test
    fun stickyNoteViewModelTest_correctlyDeletesNote() = runBlocking {
        viewModel.onAction(StickyNoteAction.AddNote)
        var state = viewModel.state.value

        viewModel.onAction(StickyNoteAction.RemoveNote(state.notes[0]))
        delay(100)

        state = viewModel.state.value
        assertTrue(state.notes.isEmpty())
        delay(100)
    }

    @Test
    fun stickyNoteViewModelTest_correctlyUpdatesTitle() = runBlocking {
        viewModel.onAction(StickyNoteAction.AddNote)
        var state = viewModel.state.value

        val newTitle = "New title"
        viewModel.onAction(StickyNoteAction.UpdateTitle(newTitle, state.notes[0]))
        delay(100)

        state = viewModel.state.value
        assertEquals(state.notes[0].title, newTitle)
        delay(100)
    }

    @Test
    fun stickyNoteViewModelTest_correctlyUpdatesDescription() = runBlocking {
        viewModel.onAction(StickyNoteAction.AddNote)
        var state = viewModel.state.value

        val newDescription = "New description"
        viewModel.onAction(StickyNoteAction.UpdateDescription(newDescription, state.notes[0]))
        delay(100)

        state = viewModel.state.value
        assertEquals(state.notes[0].description, newDescription)
        delay(100)
    }

}
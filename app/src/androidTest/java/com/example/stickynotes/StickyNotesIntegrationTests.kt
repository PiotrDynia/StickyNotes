package com.example.stickynotes

import android.content.Context
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.stickynotes.composables.StickyNoteScreen
import com.example.stickynotes.database.StickyNoteDao
import com.example.stickynotes.database.StickyNoteDatabase
import com.example.stickynotes.state.StickyNoteAction
import com.example.stickynotes.state.StickyNoteViewModel
import com.example.stickynotes.ui.theme.StickyNotesTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StickyNotesIntegrationTests {
    @get:Rule
    val testRule = createComposeRule()
    private lateinit var stickyNoteDao: StickyNoteDao
    private lateinit var db: StickyNoteDatabase
    private lateinit var viewModel: StickyNoteViewModel

    @Before
    fun setup() {
        testRule.setContent {
            val context = ApplicationProvider.getApplicationContext<Context>()
            db = Room.inMemoryDatabaseBuilder(
                context, StickyNoteDatabase::class.java
            ).build()
            stickyNoteDao = db.dao()
            viewModel = StickyNoteViewModel(stickyNoteDao)

            val state by viewModel.state.collectAsState()
            StickyNoteScreen(
                state = state,
                onAction = viewModel::onAction
            )
        }
    }
    @Test
    fun stickyNotesIntegrationTests_appIsCreated() {
        testRule
            .onNodeWithText("Your sticky notes (swipe to delete)")
            .assertIsDisplayed()
    }

    @Test
    fun stickyNotesIntegrationTests_clickingPlusCreatesNote() {
        testRule
            .onNodeWithContentDescription("Add sticky note")
            .assertExists()
            .performClick()

        runBlocking {
            delay(100)
        }

        val state = viewModel.state.value

        assert(state.notes.isNotEmpty())
    }
}
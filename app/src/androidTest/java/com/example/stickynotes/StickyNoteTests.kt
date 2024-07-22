package com.example.stickynotes

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.stickynotes.composables.StickyNote
import org.junit.Rule
import org.junit.Test

class StickyNoteTests {
    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun stickyNoteTest_noteIsCreated() {
        testRule.setContent {
            StickyNote(
                title = "Test title",
                description = "Test description",
                onTitleChange = {},
                onDescriptionChange = {},
                onRemove = {  },
                backgroundColor = Color.Blue
            )
        }
        testRule
            .onNodeWithText("Test title")
            .assertIsDisplayed()
    }
}
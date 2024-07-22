package com.example.stickynotes

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class StickyNotesIntegrationTests {
    @get:Rule
    val testRule = createComposeRule()

    @Test
    fun stickyNotesIntegrationTests_appIsCreated() {
        testRule.setContent {

        }
    }
}
package com.example.stickynotes

import androidx.lifecycle.ViewModel
import com.example.stickynotes.ui.theme.LightBlue
import com.example.stickynotes.ui.theme.LightGreen
import com.example.stickynotes.ui.theme.LightPink
import com.example.stickynotes.ui.theme.LightPurple
import com.example.stickynotes.ui.theme.LightYellow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StickyNoteViewModel : ViewModel() {
    private val _state = MutableStateFlow(StickyNoteState())
    val state: StateFlow<StickyNoteState> = _state.asStateFlow()

    private var currentColorIndex = 0
    private val stickyNoteBackgroundColorPalette = listOf(LightYellow, LightGreen, LightBlue, LightPink, LightPurple)

    fun addNote() {
        val newNote = StickyNoteContent(
            title = _state.value.title,
            description = _state.value.description,
            color = stickyNoteBackgroundColorPalette[currentColorIndex]
            )

        currentColorIndex = (currentColorIndex + 1) % stickyNoteBackgroundColorPalette.size

        _state.update { currentState ->
            currentState.copy(
                notes = currentState.notes + newNote,
                title = "",
                description = ""
            )
        }
    }

    fun updateTitle(newTitle: String) {
        _state.update { currentState ->
            currentState.copy(title = newTitle)
        }
    }

    fun updateDescription(newDescription: String) {
        _state.update { currentState ->
            currentState.copy(description = newDescription)
        }
    }
}
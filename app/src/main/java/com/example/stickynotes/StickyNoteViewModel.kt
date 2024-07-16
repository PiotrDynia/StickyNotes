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
    private var id = 1

    fun addNote() {
        val newNote = StickyNoteData(
            id = id,
            color = stickyNoteBackgroundColorPalette[currentColorIndex]
            )

        currentColorIndex = (currentColorIndex + 1) % stickyNoteBackgroundColorPalette.size
        id++

        _state.update { currentState ->
            currentState.copy(
                notes = currentState.notes + newNote
            )
        }
    }

    fun updateTitle(newTitle: String, note: StickyNoteData) {
        _state.update { currentState ->
            currentState.notes.find { it.id == note.id }?.let { note ->
                note.title = newTitle
            }
            currentState.copy(title = newTitle)
        }
    }

    fun updateDescription(newDescription: String, note: StickyNoteData) {
        _state.update { currentState ->
            currentState.notes.find { it.id == note.id }?.let { note ->
                note.description = newDescription
            }
            currentState.copy(description = newDescription)
        }
    }
}
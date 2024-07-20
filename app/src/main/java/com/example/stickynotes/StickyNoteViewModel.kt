package com.example.stickynotes

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stickynotes.ui.theme.LightBlue
import com.example.stickynotes.ui.theme.LightGreen
import com.example.stickynotes.ui.theme.LightPink
import com.example.stickynotes.ui.theme.LightPurple
import com.example.stickynotes.ui.theme.LightYellow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val MAX_TITLE_LENGTH = 40
private const val MAX_DESCRIPTION_LENGTH = 330

class StickyNoteViewModel(
    private val dao: StickyNoteDao
) : ViewModel() {
    private val _state = MutableStateFlow(StickyNoteState())
    val state: StateFlow<StickyNoteState> = _state.asStateFlow()

    private var currentColorIndex = 0
    private val stickyNoteBackgroundColorPalette =
        listOf(LightYellow, LightGreen, LightBlue, LightPink, LightPurple)

    fun onAction(action: StickyNoteAction) {
        when (action) {
            StickyNoteAction.AddNote -> {
                val newNote = StickyNoteData(
                    color = stickyNoteBackgroundColorPalette[currentColorIndex].toArgb()
                )

                currentColorIndex = (currentColorIndex + 1) % stickyNoteBackgroundColorPalette.size

                _state.update { currentState ->
                    currentState.copy(
                        notes = currentState.notes + newNote
                    )
                }

                viewModelScope.launch {
                    delay(100)
                    _state.update { currentState ->
                        currentState.copy(
                            notes = currentState.notes.map { note ->
                                if (note.id == newNote.id) note.copy(visible = true) else note
                            }
                        )
                    }
                    dao.upsertStickyNote(newNote)
                }
            }

            is StickyNoteAction.UpdateDescription -> {
                if (action.newDescription.length <= MAX_DESCRIPTION_LENGTH) {
                    _state.update { currentState ->
                        currentState.copy(
                            notes = currentState.notes.map { note ->
                                if (note.id == action.note.id)
                                    note.copy(description = action.newDescription)
                                else note
                            }
                        )
                    }
                    viewModelScope.launch {
                        dao.upsertStickyNote(action.note.copy(description = action.newDescription))
                    }
                }
            }

            is StickyNoteAction.UpdateTitle -> {
                if (action.newTitle.length <= MAX_TITLE_LENGTH) {
                    _state.update { currentState ->
                        currentState.copy(
                            notes = currentState.notes.map { note ->
                                if (note.id == action.note.id) note.copy(title = action.newTitle)
                                else note
                            }
                        )
                    }
                    viewModelScope.launch {
                        dao.upsertStickyNote(action.note.copy(title = action.newTitle))
                    }
                }
            }

            is StickyNoteAction.RemoveNote -> {
                _state.update { currentState ->
                    val mutableList = currentState.notes.toMutableList()
                    mutableList.remove(action.note)
                    currentState.copy(notes = mutableList.toList())
                }
                viewModelScope.launch {
                    dao.deleteStickyNote(action.note)
                }
            }
        }
    }
}
package com.example.stickynotes.state

import com.example.stickynotes.utils.StickyNoteData

sealed interface StickyNoteAction {
    data object AddNote : StickyNoteAction
    data class UpdateTitle(val newTitle: String, val note: StickyNoteData) : StickyNoteAction
    data class UpdateDescription(val newDescription: String, val note: StickyNoteData) :
        StickyNoteAction
    data class RemoveNote(val note: StickyNoteData) : StickyNoteAction
}
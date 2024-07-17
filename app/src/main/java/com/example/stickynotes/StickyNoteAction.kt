package com.example.stickynotes

sealed interface StickyNoteAction {
    data object AddNote: StickyNoteAction
    data class UpdateTitle(val newTitle: String, val note: StickyNoteData) : StickyNoteAction
    data class UpdateDescription(val newDescription: String, val note: StickyNoteData) : StickyNoteAction
}
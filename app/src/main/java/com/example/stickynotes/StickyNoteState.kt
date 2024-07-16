package com.example.stickynotes

data class StickyNoteState(
    val notes: List<StickyNoteContent> = emptyList(),
    val title: String = "",
    val description: String = ""
)

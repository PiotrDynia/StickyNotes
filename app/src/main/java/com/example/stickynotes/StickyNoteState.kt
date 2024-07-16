package com.example.stickynotes

data class StickyNoteState(
    val notes: List<StickyNoteData> = emptyList(),
    val title: String = "",
    val description: String = ""
)

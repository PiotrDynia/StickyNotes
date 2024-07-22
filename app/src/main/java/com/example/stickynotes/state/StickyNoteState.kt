package com.example.stickynotes.state

import com.example.stickynotes.utils.StickyNoteData

data class StickyNoteState(
    val notes: List<StickyNoteData> = emptyList(),
    val title: String = "",
    val description: String = ""
)

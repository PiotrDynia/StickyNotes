package com.example.stickynotes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

data class StickyNoteData(
    val id: Int,
    val initialTitle: String = "",
    val initialDescription: String = "",
    val color: Color
) {
    var title by mutableStateOf(initialTitle)
    var description by mutableStateOf(initialDescription)
}

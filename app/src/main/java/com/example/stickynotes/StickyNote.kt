package com.example.stickynotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.max

@Composable
fun StickyNote(title: String, description: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color(0xFF5FE3EC))
            .clip(shape = RoundedCornerShape(15.dp)),
    ) {
        Column (modifier = Modifier.padding(8.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                maxLines = 3
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 15
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StickyNotePreview() {
    StickyNote(title = "Hello", description = "Lorem ipsum".repeat(10))
}
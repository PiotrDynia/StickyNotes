package com.example.stickynotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stickynotes.ui.theme.LightPurple

@Composable
fun StickyNote(
    title: String,
    description: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .background(backgroundColor),
    ) {
        Column (modifier = Modifier.padding(8.dp)) {
            TextField(
                value = title,
                onValueChange = onTitleChange,
                textStyle = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                label = {  },
                maxLines = 2,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
            TextField(
                value = description,
                onValueChange = onDescriptionChange,
                textStyle = MaterialTheme.typography.bodyLarge,
                label = {  },
                maxLines = 15,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StickyNotePreview() {
    StickyNote(
        title = "Hello",
        onTitleChange = {},
        onDescriptionChange = {},
        backgroundColor = LightPurple,
        description = "Lorem ipsum".repeat(10)
    )
}
package com.example.stickynotes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stickynotes.ui.theme.LightPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StickyNote(
    title: String,
    description: String,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onRemove: () -> Unit,
    backgroundColor: Color,
    modifier: Modifier = Modifier) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when(it) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    onRemove()
                }
                SwipeToDismissBoxValue.EndToStart -> {
                    onRemove()
                }
                SwipeToDismissBoxValue.Settled -> return@rememberSwipeToDismissBoxState  false
            }
            return@rememberSwipeToDismissBoxState true
        },
        positionalThreshold = { it * .25f }
    )
    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = { }
    ) {
        Box(
            modifier = modifier
                .clip(shape = RoundedCornerShape(15.dp))
                .size(316.dp)
                .background(backgroundColor)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                TextField(
                    value = title,
                    onValueChange = onTitleChange,
                    textStyle = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                    ),
                    label = { },
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
                    label = { },
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
}

@Preview(showBackground = true)
@Composable
private fun StickyNotePreview() {
    StickyNote(
        title = "Hello",
        onTitleChange = {},
        onDescriptionChange = {},
        onRemove = {},
        backgroundColor = LightPurple,
        description = "Lorem ipsum".repeat(10)
    )
}
package com.example.stickynotes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun StickyNoteScreen(
    state: StickyNoteState,
    onAddNote: () -> Unit,
    onTitleChange: (String, StickyNoteData) -> Unit,
    onDescriptionChange: (String, StickyNoteData) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAddNote()
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add sticky note"
                )
            }
        },
        modifier = Modifier.padding(vertical = 64.dp)
    ) { _ ->
        LazyColumn (
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = stringResource(R.string.headline_text),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            items(
                items = state.notes,
                key = { note -> note.id }
            ) { note ->
                StickyNote(
                    title = note.title,
                    description = note.description,
                    onTitleChange = { newTitle -> onTitleChange(newTitle, note) },
                    onDescriptionChange = { newDescription -> onDescriptionChange(newDescription, note) },
                    backgroundColor = note.color
                )
            }
        }
    }
}
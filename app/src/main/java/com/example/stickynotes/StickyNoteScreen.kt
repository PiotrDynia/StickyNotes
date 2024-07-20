package com.example.stickynotes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickyNoteScreen(
    state: StickyNoteState,
    onAction: (StickyNoteAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onAction(StickyNoteAction.AddNote)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add sticky note"
                )
            }
        },
        modifier = Modifier.padding(vertical = 64.dp)
    ) { _ ->
        LaunchedEffect(state.notes.size) {
            listState.animateScrollToItem(state.notes.size)
        }
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState
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
                AnimatedVisibility(
                    visible = note.visible,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically(),
                    modifier = Modifier.animateItemPlacement()
                ) {
                    StickyNote(
                        title = note.title,
                        description = note.description,
                        onTitleChange = { newTitle ->
                            onAction(
                                StickyNoteAction.UpdateTitle(
                                    newTitle,
                                    note
                                )
                            )
                        },
                        onDescriptionChange = { newDescription ->
                            onAction(
                                StickyNoteAction.UpdateDescription(
                                    newDescription,
                                    note
                                )
                            )
                        },
                        onRemove = { onAction(StickyNoteAction.RemoveNote(note)) },
                        backgroundColor = note.colorObj
                    )
                }
            }
        }
    }
}
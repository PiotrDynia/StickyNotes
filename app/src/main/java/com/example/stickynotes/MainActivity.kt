package com.example.stickynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.stickynotes.ui.theme.StickyNotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StickyNotesTheme {
                val db by lazy {
                    Room.databaseBuilder(
                        applicationContext,
                        StickyNoteDatabase::class.java,
                        "sticky_notes.db"
                    ).build()
                }
                val viewModel by viewModels<StickyNoteViewModel>(
                    factoryProducer = {
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                if (modelClass.isAssignableFrom(StickyNoteViewModel::class.java)) {
                                    @Suppress("UNCHECKED_CAST")
                                    return StickyNoteViewModel(db.dao()) as T
                                }
                                throw IllegalArgumentException("Unknown ViewModel class")
                            }
                        }
                    }
                )
                val state by viewModel.state.collectAsState()
                StickyNoteScreen(
                    state = state,
                    onAction = viewModel::onAction
                )
            }
        }
    }
}

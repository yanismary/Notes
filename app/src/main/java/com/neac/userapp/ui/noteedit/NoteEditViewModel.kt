package com.neac.userapp.ui.noteedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.neac.userapp.data.local.NoteEntity
import com.neac.userapp.data.repository.NoteRepository
import com.neac.userapp.ui.navigation.NoteEditRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteEditViewModel @Inject constructor(
    private val repository: NoteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val noteId: Long = savedStateHandle.toRoute<NoteEditRoute>().noteId
    val isEditing: Boolean = noteId != -1L

    var title by mutableStateOf("")
        private set
    var content by mutableStateOf("")
        private set

    private var originalNote: NoteEntity? = null

    init {
        if (isEditing) {
            viewModelScope.launch {
                repository.getNoteById(noteId)?.let { note ->
                    originalNote = note
                    title = note.title
                    content = note.content
                }
            }
        }
    }

    fun onTitleChange(newTitle: String) { title = newTitle }
    fun onContentChange(newContent: String) { content = newContent }

    fun save(onSaved: () -> Unit) {
        if (title.isBlank()) return
        viewModelScope.launch {
            if (isEditing) {
                originalNote?.let { note ->
                    repository.updateNote(
                        note.copy(
                            title = title,
                            content = content,
                            updatedAt = System.currentTimeMillis()
                        )
                    )
                }
            } else {
                repository.insertNote(NoteEntity(title = title, content = content))
            }
            onSaved()
        }
    }
}

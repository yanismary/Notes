package com.neac.userapp.ui.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neac.userapp.data.local.NoteEntity
import com.neac.userapp.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val uiState = repository.getAllNotes()
        .map { notes -> NoteListUiState(notes = notes, isLoading = false) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = NoteListUiState(isLoading = true)
        )

    fun deleteNote(note: NoteEntity) = viewModelScope.launch {
        try {
            repository.deleteNote(note)
        } catch (e: Exception) {
            // errorMessage pourrait être mis à jour dans uiState ici si nécessaire
        }
    }

    fun togglePin(note: NoteEntity) = viewModelScope.launch {
        repository.updateNote(
            note.copy(
                isPinned = !note.isPinned,
                updatedAt = System.currentTimeMillis()
            )
        )
    }
}

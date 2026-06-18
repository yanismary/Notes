package com.neac.userapp.ui.notelist

import com.neac.userapp.data.local.NoteEntity

data class NoteListUiState(
    val notes: List<NoteEntity> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

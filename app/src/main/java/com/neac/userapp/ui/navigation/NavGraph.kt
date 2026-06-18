package com.neac.userapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neac.userapp.ui.noteedit.NoteEditScreen
import com.neac.userapp.ui.notelist.NoteListScreen

@Composable
fun NotesApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NoteListRoute
    ) {
        composable<NoteListRoute> {
            NoteListScreen(
                onNoteClick = { id -> navController.navigate(NoteEditRoute(id)) },
                onAddClick = { navController.navigate(NoteEditRoute()) }
            )
        }
        composable<NoteEditRoute> {
            NoteEditScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}

package com.example.e_librarium.ui.theme.screens.books

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun EditBooksScreen(navController: NavController){

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Edit Books Screen Preview"
)
@Composable
fun EditBooksScreenPreview(){
    ELibrariumTheme {
        EditBooksScreen(navController = rememberNavController())
    }
}
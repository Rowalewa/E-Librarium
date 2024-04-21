package com.example.e_librarium.ui.theme.screens.books

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun BooksHomeScreen(navController: NavController){

}
@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Books Home Screen Preview"
)
@Composable
fun BooksHomeScreenPreview(){
    ELibrariumTheme {
        BooksHomeScreen(navController = rememberNavController())
    }
}
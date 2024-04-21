package com.example.e_librarium.ui.theme.screens.books

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun AddBooksScreen(navController: NavController){

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Add Books Screen Preview"
)
@Composable
fun AddBooksScreenPreview(){
    ELibrariumTheme {
        AddBooksScreen(navController = rememberNavController())
    }
}
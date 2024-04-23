package com.example.e_librarium.ui.theme.screens.books

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.navigation.ROUTE_ADD_BOOKS
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun BooksHomeScreen(navController: NavController){
    Column {
        Button(onClick = { navController.navigate(ROUTE_ADD_BOOKS) }) {
            Text(text = "Add Books")
        }
    }
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
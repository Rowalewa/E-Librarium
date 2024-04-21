package com.example.e_librarium.ui.theme.screens.borrowing

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun BorrowBooksScreen(navController: NavController){

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Borrow Books Screen Preview"
)
@Composable
fun BorrowBooksScreenPreview(){
    ELibrariumTheme {
        BorrowBooksScreen(navController = rememberNavController())
    }
}
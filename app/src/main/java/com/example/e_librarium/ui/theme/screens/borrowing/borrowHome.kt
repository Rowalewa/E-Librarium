package com.example.e_librarium.ui.theme.screens.borrowing

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun BorrowHomeScreen(navController: NavController){

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Borrow Books Home Screen Preview"
)
@Composable
fun BorrowBooksHomeScreenPreview(){
    ELibrariumTheme {
        BorrowHomeScreen(navController = rememberNavController())
    }
}
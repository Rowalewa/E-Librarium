package com.example.e_librarium.ui.theme.screens.returning

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun ReturnBooksScreen(navController: NavController){

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Return Books Screen Preview"
)
@Composable
fun ReturnBooksScreenPreview(){
    ELibrariumTheme {
        ReturnBooksScreen(navController = rememberNavController())
    }
}
package com.example.e_librarium.ui.theme.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun HomeScreen(navController: NavController){

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Home Screen Preview"
)
@Composable
fun HomeScreenPreview(){
    ELibrariumTheme {
        HomeScreen(navController = rememberNavController())
    }
}
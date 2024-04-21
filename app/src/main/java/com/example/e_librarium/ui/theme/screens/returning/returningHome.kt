package com.example.e_librarium.ui.theme.screens.returning

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun ReturningHomeScreen(navController: NavController){

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Return Home Screen Preview"
)
@Composable
fun ReturningHomeScreenPreview(){
    ELibrariumTheme {
        ReturningHomeScreen(navController = rememberNavController())
    }
}
package com.example.e_librarium.ui.theme.screens.clients

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun ClientHomeScreen(navController: NavController){

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Client Home Screen Preview"
)
@Composable
fun ClientHomeScreenPreview(){
    ELibrariumTheme {
        ClientHomeScreen(navController = rememberNavController())
    }
}
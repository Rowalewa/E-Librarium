package com.example.e_librarium.ui.theme.screens.staff

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun StaffLogInScreen(navController: NavController){

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Staff LogIn Screen Preview"
)
@Composable
fun StaffLogInScreenPreview(){
    ELibrariumTheme {
        StaffLogInScreen(navController = rememberNavController())
    }
}
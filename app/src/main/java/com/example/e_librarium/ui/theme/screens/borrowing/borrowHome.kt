package com.example.e_librarium.ui.theme.screens.borrowing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.data.AuthViewModel
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun BorrowHomeScreen(navController: NavController){
    val context = LocalContext.current
    Column {
        Button(onClick = {
            val myClientLogout = AuthViewModel(navController, context)
            myClientLogout.clientlogout()
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text(text = "Sign Out")
        }
    }

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
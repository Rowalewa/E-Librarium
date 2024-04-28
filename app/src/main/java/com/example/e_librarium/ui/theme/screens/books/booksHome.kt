package com.example.e_librarium.ui.theme.screens.books

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
import com.example.e_librarium.navigation.ROUTE_ADD_BOOKS
import com.example.e_librarium.navigation.ROUTE_EDIT_BOOKS
import com.example.e_librarium.navigation.ROUTE_VIEW_BOOKS
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun BooksHomeScreen(navController: NavController){
    val context = LocalContext.current
    Column {
        Button(onClick = { navController.navigate(ROUTE_ADD_BOOKS) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green
            )
        ) {
            Text(text = "\uD83D\uDCC1 Add Books")
        }
        Button(onClick = { navController.navigate(ROUTE_VIEW_BOOKS) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            )
        ) {
            Text(text = "View Books")
        }
//        Button(onClick = { navController.navigate("$ROUTE_EDIT_BOOKS/{bookId}") },
//            modifier = Modifier.fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color.Blue
//            )
//        ) {
//            Text(text = "Edit Books")
//        }
        Button(onClick = {
            val myStaffLogout = AuthViewModel(navController, context)
            myStaffLogout.stafflogout()
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text(text = "\uD83D\uDEB6 Sign Out \uD83D\uDEB6\u200D♀\uFE0F")
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
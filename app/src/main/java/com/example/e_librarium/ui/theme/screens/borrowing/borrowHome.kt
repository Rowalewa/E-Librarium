package com.example.e_librarium.ui.theme.screens.borrowing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.data.AuthViewModel
import com.example.e_librarium.data.BooksViewModel
import com.example.e_librarium.navigation.ROUTE_BORROW_BOOKS
import com.example.e_librarium.navigation.ROUTE_EDIT_BOOKS
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun BorrowHomeScreen(navController: NavHostController, clientId: String){
    val context = LocalContext.current
    val booksViewModel = remember { BooksViewModel(navController, context) }
    val borrowedBooks = booksViewModel.getBorrowedBooksForClient(clientId)

    Column {
        Button(
            onClick = {
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
        Button(
            onClick = {
                navController.navigate(ROUTE_BORROW_BOOKS)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow
            )
        ) {
            Text(text = "Borrow Books Screen")
        }
        Column (
            modifier = Modifier.background(color = Color.Red)
        ){
            borrowedBooks.forEach { book ->
                Text(text = "Borrowed Book: ${book.bookId}") // Display the borrowed book information
//            Text(text = "Borrowed Book: ${book.bookTitle}")
            }
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
        BorrowHomeScreen(navController = rememberNavController(), clientId = "")
    }
}
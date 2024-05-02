package com.example.e_librarium.ui.theme.screens.clients

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.e_librarium.data.BooksViewModel
import com.example.e_librarium.models.BorrowingBook

@Composable
fun ViewBorrowedBooks(navController: NavHostController, clientId: String){
    val context = LocalContext.current
    val booksViewModel = remember { BooksViewModel(navController, context) }
    val (borrowedBooks, setBorrowedBooks) = remember { mutableStateOf<List<BorrowingBook>>(emptyList()) }

    LaunchedEffect(clientId) {
        try {
            booksViewModel.getBorrowedBooksForClient(clientId) { books ->
                setBorrowedBooks(books)
                Toast.makeText(context, "Fetching borrowed books", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            // Handle the error, e.g., show a toast or log the error
            // For simplicity, a toast message is shown here
            Toast.makeText(context, "Failed to fetch borrowed books", Toast.LENGTH_SHORT).show()
        }
    }
    Column {
        LazyColumn {
            items(borrowedBooks) { book ->
                BookItems(
                    bookId = book.bookId
                )
            }
        }
    }
}

@Composable
fun BookItems(
    bookId: String,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = 20.dp,
            top = 0.dp,
            end = 20.dp,
            bottom = 0.dp
        )
        .clip(shape = CutCornerShape(20.dp))
        ,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color.Green)
                .fillMaxWidth()
        ) {
            Text(
                text = "Book Id: $bookId",
                color = Color.Black
            )
        }
    }
    Spacer(modifier = Modifier.height(40.dp))
}
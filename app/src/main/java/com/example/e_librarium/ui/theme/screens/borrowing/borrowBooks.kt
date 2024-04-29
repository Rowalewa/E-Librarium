package com.example.e_librarium.ui.theme.screens.borrowing

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.data.BooksViewModel
import com.example.e_librarium.models.Books
import com.example.e_librarium.models.Clients
import com.example.e_librarium.ui.theme.ELibrariumTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun BorrowBooksScreen(navController: NavHostController){
    val context = LocalContext.current
    val booksViewModel = BooksViewModel(navController, context)

    val bookId by remember {
        mutableStateOf("")
    }
    val clientId by remember {
        mutableStateOf("")
    }
    var borrowDate by remember { mutableStateOf("") }
    var returnDate by remember { mutableStateOf("") }

    var mClientId by remember {
        mutableStateOf(clientId)
    }
    var mBookId by remember {
        mutableStateOf(bookId)
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = mBookId,
            onValueChange = { mBookId = it },
            label = { Text("Book ID") }
        )
        OutlinedTextField(
            value = mClientId,
            onValueChange = { mClientId = it },
            label = { Text("Client ID") }
        )
        OutlinedTextField(
            value = borrowDate,
            onValueChange = { borrowDate = it },
            label = { Text("Borrow Date") }
        )
        OutlinedTextField(
            value = returnDate,
            onValueChange = { returnDate = it },
            label = { Text("Return Date") }
        )
        Button(
            onClick = {
                booksViewModel.borrowBook(bookId, clientId, borrowDate, returnDate)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Borrow Book")
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Borrow Books Screen Preview"
)
@Composable
fun BorrowBooksScreenPreview(){
    ELibrariumTheme {
        BorrowBooksScreen(navController = rememberNavController())
    }
}
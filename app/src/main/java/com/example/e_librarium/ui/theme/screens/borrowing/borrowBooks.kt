package com.example.e_librarium.ui.theme.screens.borrowing

import android.app.DatePickerDialog
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.e_librarium.R
import com.example.e_librarium.data.BooksViewModel
import com.example.e_librarium.models.Books
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun BorrowBooksScreen(navController: NavHostController, bookId: String, clientId: String){
    val context = LocalContext.current
    val booksViewModel = BooksViewModel(navController, context)

    var mBookId by remember {
        mutableStateOf(TextFieldValue(bookId))
    }
    var borrowDate by remember { mutableStateOf(TextFieldValue("")) }
    var isBorrowDateExpanded by remember { mutableStateOf(false) }
    var returnDate by remember { mutableStateOf(TextFieldValue("")) }
    var isReturnDateExpanded by remember { mutableStateOf(false) }

    Log.d("Firebase", "Book ID: $bookId")
    val currentDataRef = FirebaseDatabase.getInstance().getReference().child("Books/$bookId")
    val path = "Books/$bookId"
    Log.d("Firebase", "Database Reference Path: $path")
    Log.d("Firebase", "Fetching book with ID: $bookId")
    currentDataRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
                val book = snapshot.getValue(Books::class.java)
                if (book != null) {
                    mBookId = TextFieldValue(book.bookId)
                } else {
                    Log.e("Firebase", "Failed to parse book data")
                }
            } else {
                Log.e("Firebase", "Snapshot does not exist")
            }
        }

        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
        }
    })


    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.borrow_books),
            contentDescription = "Borrow books image",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(
                value = mBookId,
                onValueChange = { mBookId = it },
                label = { Text("Book ID") }
            )
            OutlinedTextField(
                value = clientId,
                onValueChange = {  },
                label = { Text("Client ID") }
            )
            OutlinedTextField(
                value = borrowDate,
                onValueChange = { borrowDate = it },
                label = { Text("Borrow Date") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { isBorrowDateExpanded = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Pick Date")
                    }
                }
            )

            if (isBorrowDateExpanded) {
                val today = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(year, month, day)
                        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                        borrowDate = TextFieldValue(sdf.format(selectedDate.time))
                        isBorrowDateExpanded = false
                    },
                    today.get(Calendar.YEAR),
                    today.get(Calendar.MONTH),
                    today.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            OutlinedTextField(
                value = returnDate,
                onValueChange = { returnDate = it },
                label = { Text("Return Date") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { isReturnDateExpanded = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Pick Date")
                    }
                }
            )

            if (isReturnDateExpanded) {
                val today = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(year, month, day)
                        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                        returnDate = TextFieldValue(sdf.format(selectedDate.time))
                        isReturnDateExpanded = false
                    },
                    today.get(Calendar.YEAR),
                    today.get(Calendar.MONTH),
                    today.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            Button(
                onClick = {
                    booksViewModel.borrowBook(
                        mBookId.text.trim(),
                        clientId,
                        borrowDate.text.trim(),
                        returnDate.text.trim()
                    )
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Borrow Book")
            }
        }
    }
}
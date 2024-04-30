package com.example.e_librarium.ui.theme.screens.borrowing

import android.app.DatePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.R
import com.example.e_librarium.data.BooksViewModel
import com.example.e_librarium.models.BorrowingBook
import com.example.e_librarium.ui.theme.ELibrariumTheme
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Locale

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
    var borrowDate by remember { mutableStateOf(TextFieldValue("")) }
    var isBorrowDateExpanded by remember { mutableStateOf(false) }
    var returnDate by remember { mutableStateOf(TextFieldValue("")) }
    var isReturnDateExpanded by remember { mutableStateOf(false) }

    var mClientId by remember {
        mutableStateOf(clientId)
    }
    var mBookId by remember {
        mutableStateOf(bookId)
    }

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
                value = mClientId,
                onValueChange = { mClientId = it },
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
                        bookId,
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
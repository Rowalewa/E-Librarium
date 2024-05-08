package com.example.e_librarium.ui.theme.screens.clients

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.e_librarium.data.BooksViewModel
import com.example.e_librarium.models.BorrowingBook
import com.example.e_librarium.navigation.ROUTE_BORROW_HOME
import com.google.firebase.Firebase
import com.google.firebase.database.database

@Composable
fun ViewBorrowedBooks(navController: NavHostController, clientId: String){
    val context = LocalContext.current
    val booksRepository = BooksViewModel(navController, context)
    val emptyBookState = remember { mutableStateOf(BorrowingBook()) }
    val emptyBookListState = remember {
        mutableStateListOf<BorrowingBook>()
    }

    val books = booksRepository.viewClientBooks(emptyBookState, emptyBookListState)

    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = clientId,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { navController.navigate("$ROUTE_BORROW_HOME/$clientId") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Back")
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(books){
                BookItems(bookId = it.bookId)
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
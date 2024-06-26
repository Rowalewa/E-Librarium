package com.example.e_librarium.ui.theme.screens.returning

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.e_librarium.R
import com.example.e_librarium.data.BooksViewModel
import com.example.e_librarium.models.BorrowingBook
import com.example.e_librarium.navigation.ROUTE_RETURN_BOOKS
import com.example.e_librarium.ui.theme.screens.books.StaffAppTopBar
import com.example.e_librarium.ui.theme.screens.books.StaffBottomAppBar

@Composable
fun ViewClientBorrowedBooks(navController: NavHostController, clientId: String, staffId: String){
    val context = LocalContext.current
    val booksViewModel = remember { BooksViewModel(navController, context) }
    var borrowedBooks by remember { mutableStateOf<List<BorrowingBook>>(emptyList()) }

    LaunchedEffect(Unit) {
        booksViewModel.getBorrowedBooksForClient(clientId) { books ->
            borrowedBooks = books
        }
    }

    Box{
        Image(
            painter = painterResource(id = R.drawable.view_my_borrowed_books),
            contentDescription = "View Client Wallpaper",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column{
            Box (
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ){
                StaffAppTopBar(navController, staffId)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                LazyColumn {
                    items(borrowedBooks) { book ->
                        BookItems(
                            bookId = book.bookId,
                            bookTitle = book.bookTitle,
                            bookAuthor = book.bookAuthor,
                            bookISBNNumber = book.bookISBNNumber,
                            bookGenre = book.bookGenre,
                            bookPublisher = book.bookPublisher,
                            bookSynopsis = book.bookSynopsis,
                            bookImageUrl = book.bookImageUrl,
                            borrowDate = book.borrowDate,
                            returnDate = book.returnDate,
                            navController,
                            clientId,
                            staffId
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            StaffBottomAppBar(navController, staffId)
        }
    }
}

@Composable
fun BookItems(
    bookId: String,
    bookTitle: String,
    bookAuthor: String,
    bookISBNNumber: String,
    bookGenre: String,
    bookPublisher: String,
    bookSynopsis: String,
    bookImageUrl: String,
    borrowDate: String,
    returnDate: String,
    navController: NavController,
    clientId: String,
    staffId: String
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
        .border(width = 5.dp, shape = CutCornerShape(20.dp), color = Color.Blue)
        ,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color.Green)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(bookImageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(400.dp)
                    .padding(18.dp)
            )
            Text(
                text = "Book Id: $bookId",
                color = Color.Black
            )
            Text(
                text = "Book Title: $bookTitle",
                color = Color.Black
            )
            Text(
                text = "Book Author: $bookAuthor",
                color = Color.Black
            )
            Text(
                text = "Book ISBN Number: $bookISBNNumber",
                color = Color.Black
            )
            Text(
                text = "Book Genre: $bookGenre",
                color = Color.Black
            )
            Text(
                text = "Book Publisher: $bookPublisher",
                color = Color.Black
            )
            Text(
                text = "Book Synopsis: $bookSynopsis",
                color = Color.Black
            )
            Text(
                text = "Book Borrow Date: $borrowDate",
                color = Color.Black
            )
            Text(
                text = "Book Return Date: $returnDate",
                color = Color.Black
            )
            Button(onClick = {
                navController.navigate("$ROUTE_RETURN_BOOKS/$clientId/$bookId/$staffId")
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 0.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    ),
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text(text = "Return")
            }
        }
    }
    Spacer(modifier = Modifier.height(80.dp))
}
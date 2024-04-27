package com.example.e_librarium.ui.theme.screens.books

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.e_librarium.data.BooksViewModel
import com.example.e_librarium.models.Books
import com.example.e_librarium.navigation.ROUTE_BOOKS_HOME

@Composable
fun ViewBooksScreen(navController: NavHostController){
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val context = LocalContext.current
        val booksRepository = BooksViewModel(navController, context)
        val emptyBookState = remember { mutableStateOf(Books("","","","","","","","","","","","","","","","","","")) }
        val emptyBookListState = remember { mutableStateListOf<Books>() }

        val books = booksRepository.viewBooks(emptyBookState, emptyBookListState)


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "All Books",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = { navController.navigate(ROUTE_BOOKS_HOME) },
                colors = ButtonDefaults.buttonColors(Color.Blue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    )) {
                Text(
                    text = "Back to Home Screen",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(
                    start = 0.dp,
                    end = 0.dp,
                    bottom = 20.dp,
                    top = 0.dp
                )
            ){
               items(books){
                   BookItem(
                       bookTitle = it.bookTitle,
                       bookAuthor = it.bookAuthor,
                       bookYearOfPublication = it.bookYearOfPublication,
                       bookPrice = it.bookPrice,
                       bookISBNNumber = it.bookISBNNumber,
                       bookPublisher = it.bookPublisher,
                       bookPublicationDate = it.bookPublicationDate,
                       bookGenre = it.bookGenre,
                       bookEdition = it.bookEdition,
                       bookLanguage = it.bookLanguage,
                       bookNumberOfPages = it.bookNumberOfPages,
                       bookAcquisitionMethod = it.bookAcquisitionMethod,
                       bookCondition = it.bookCondition,
                       bookShelfNumber = it.bookShelfNumber,
                       bookStatus = it.bookStatus,
                       bookSynopsis = it.bookSynopsis,
                       bookImageUrl = it.bookImageUrl,
                       bookId = it.bookId,
                       navController = navController,
                       bookRepository = booksRepository
                   )
                }
            }
        }
    }
}
@Composable
fun BookItem(
    bookTitle: String,
    bookAuthor: String,
    bookYearOfPublication: String,
    bookPrice: String,
    bookISBNNumber: String,
    bookPublisher: String,
    bookPublicationDate: String,
    bookGenre: String,
    bookEdition: String,
    bookLanguage: String,
    bookNumberOfPages: String,
    bookAcquisitionMethod: String,
    bookCondition: String,
    bookShelfNumber: String,
    bookStatus: String,
    bookSynopsis: String,
    bookImageUrl: String,
    bookId: String,
    navController: NavHostController,
    bookRepository:BooksViewModel
) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = 20.dp,
            top = 0.dp,
            end = 20.dp,
            bottom = 0.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier
                .background(color = Color.Blue)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Book Title: $bookTitle")
            Text(text = "Book Author: $bookAuthor")
            Text(text = "Book Year of Publication: $bookYearOfPublication")
            Text(text = "Book Price: $bookPrice")
            Text(text = "Book ISBN Number: $bookISBNNumber")
            Text(text = "Book Publisher: $bookPublisher")
            Text(text = "Book Publication Date: $bookPublicationDate")
            Text(text = "Book Genre: $bookGenre")
            Text(text = "Book Edition: $bookEdition")
            Text(text = "Book Language: $bookLanguage")
            Text(text = "Book Number of Pages: $bookNumberOfPages")
            Text(text = "Book Acquisition Method: $bookAcquisitionMethod")
            Text(text = "Book Condition: $bookCondition")
            Text(text = "Book Shelf Number: $bookShelfNumber")
            Text(text = "Book Status: $bookStatus")
            Text(text = "Book Synopsis: $bookSynopsis")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color.Green)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(bookImageUrl),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Row(
                modifier = Modifier.background(color = Color.Yellow)
            ) {
                Button(
                    onClick = {
                        bookRepository.deleteBook(bookId)
                    },
                    modifier = Modifier
                        .width(150.dp)
                        .padding(
                            start = 20.dp,
                            end = 0.dp,
                            top = 0.dp,
                            bottom = 0.dp
                        ),
                    colors = ButtonDefaults.buttonColors(Color.Red)
                ) {
                    Text(text = "Delete")
                }
                Spacer(modifier = Modifier.width(30.dp))
                Button(
                    onClick = {
//                navController.navigate("$ROUTE_UPDATE_PRODUCT/$id")
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(
                            start = 0.dp,
                            end = 20.dp,
                            top = 0.dp,
                            bottom = 0.dp
                        ),
                    colors = ButtonDefaults.buttonColors(Color.Blue)
                ) {
                    Text(text = "Update")
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
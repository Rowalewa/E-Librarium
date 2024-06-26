package com.example.e_librarium.ui.theme.screens.books

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.e_librarium.R
import com.example.e_librarium.data.BooksViewModel
import com.example.e_librarium.models.Books
import com.example.e_librarium.navigation.ROUTE_EDIT_BOOKS

@Composable
fun ViewAllBooksScreen(navController: NavHostController, staffId: String){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.view_books),
            contentDescription = "View Books",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        Column {
            Box (
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ){
                StaffAppTopBar(navController, staffId)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val context = LocalContext.current
                val booksRepository = BooksViewModel(navController, context)
                val emptyBookState = remember {
                    mutableStateOf(
                        Books(
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            0,
                            ""
                        )
                    )
                }
                val emptyBookListState = remember { mutableStateListOf<Books>() }

                val books = booksRepository.viewBooks(emptyBookState, emptyBookListState)
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "BOOKS",
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.Red
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    var searchText by remember { mutableStateOf("") }
                    Row(
                        modifier = Modifier
                            .border(
                                width = Dp.Hairline,
                                shape = CutCornerShape(10.dp),
                                color = Color.Black
                            )
                            .background(color = Color.Cyan, shape = CutCornerShape(10.dp)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            label = { Text("Search") },
                            modifier = Modifier.padding(
                                start = 10.dp,
                                end = 0.dp,
                                top = 2.dp,
                                bottom = 5.dp
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Red,
                                unfocusedContainerColor = Color.White,
                                focusedLabelColor = Color.Black,
                                unfocusedLabelColor = Color.DarkGray,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Magenta
                            )
                        )
                        IconButton(onClick = { searchText = "" }) {
                            Icon(
                                Icons.Filled.Clear,
                                contentDescription = "Clear Search",
                                tint = Color.Red
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
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
                        bookSynopsis: String,
                        bookImageUrl: String,
                        bookId: String,
                        bookQuantity: Int,
                        navController: NavHostController,
                        bookRepository: BooksViewModel
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 20.dp,
                                    top = 0.dp,
                                    end = 20.dp,
                                    bottom = 0.dp
                                )
                                .clip(shape = CutCornerShape(20.dp)),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(color = Color.Green)
                                    .fillMaxWidth()
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(bookImageUrl),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(400.dp)
                                        .padding(18.dp)
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
                                    text = "Book Year of Publication: $bookYearOfPublication",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book Price: $bookPrice",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book Quantity: $bookQuantity",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book ISBN Number: $bookISBNNumber",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book Publisher: $bookPublisher",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book Publication Date: $bookPublicationDate",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book Genre: $bookGenre",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book Edition: $bookEdition",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book Language: $bookLanguage",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book Number of Pages: $bookNumberOfPages",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book Acquisition Method: $bookAcquisitionMethod",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book Condition: $bookCondition",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book Shelf Number: $bookShelfNumber",
                                    color = Color.Black
                                )
                                Text(
                                    text = "Book Synopsis: $bookSynopsis",
                                    color = Color.Black
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
                                            navController.navigate("$ROUTE_EDIT_BOOKS/$bookId/$staffId")
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
                            }
                        }
                        Spacer(modifier = Modifier.height(70.dp))
                    }

                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(
                            start = 0.dp,
                            end = 0.dp,
                            bottom = 20.dp,
                            top = 0.dp
                        )
                    ) {
                        val filteredBooks = books.filter {
                            it.bookTitle.contains(searchText, ignoreCase = true) ||
                                    it.bookAuthor.contains(searchText, ignoreCase = true) ||
                                    it.bookGenre.contains(searchText, ignoreCase = true) ||
                                    it.bookPublisher.contains(searchText, ignoreCase = true) ||
                                    it.bookCondition.contains(searchText, ignoreCase = true) ||
                                    it.bookAcquisitionMethod.contains(searchText, ignoreCase = true) ||
                                    it.bookEdition.contains(searchText, ignoreCase = true) ||
                                    it.bookISBNNumber.contains(searchText, ignoreCase = true)
                        }
                        items(filteredBooks) {
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
                                bookSynopsis = it.bookSynopsis,
                                bookImageUrl = it.bookImageUrl,
                                bookId = it.bookId,
                                bookQuantity = it.bookQuantity,
                                navController = navController,
                                bookRepository = booksRepository
                            )
                        }
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

package com.example.e_librarium.ui.theme.screens.books

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.R
import com.example.e_librarium.data.BooksViewModel
import com.example.e_librarium.models.Books
import com.example.e_librarium.navigation.ROUTE_BOOKS_HOME
import com.example.e_librarium.navigation.ROUTE_VIEW_BOOKS
import com.example.e_librarium.ui.theme.ELibrariumTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditBooksScreen(navController: NavHostController, bookId: String){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.edit_books),
            contentDescription = "Edit books image",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState(), enabled = true, reverseScrolling = true),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val context = LocalContext.current
            val bookTitle by remember { mutableStateOf("") }
            val bookAuthor by remember { mutableStateOf("") }
            var bookPrice by remember { mutableStateOf("") }
            val bookPublisher by remember { mutableStateOf("") }
            val bookPublicationDate by remember { mutableStateOf("") }
            val bookShelfNumber by remember { mutableStateOf("") }
            val bookSynopsis by remember { mutableStateOf("") }
            val bookEdition by remember { mutableStateOf("") }
            val bookLanguage by remember { mutableStateOf("") }
            val bookNumberOfPages by remember { mutableStateOf("") }
            val bookISBNNumber by remember { mutableStateOf("") }
            val bookYearOfPublication by remember { mutableStateOf("") }




            Text(
                text = "Edit Book Record",
                fontSize = 30.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.Red,
                modifier = Modifier.padding(20.dp),
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )

            val mBookStatusOptions = listOf("Available", "Borrowed", "Reserved")
            var mIsExpanded by remember {
                mutableStateOf(false)
            }
            var mBookStatus by remember {
                mutableStateOf(mBookStatusOptions[0])
            }
            val mBookConditionOptions = listOf("New", "Used", "Damaged")
            var mIsOpen by remember {
                mutableStateOf(false)
            }
            var mBookCondition by remember {
                mutableStateOf(mBookConditionOptions[0])
            }
            val mBookGenreOptions = listOf(
                "Fiction",
                "Non-Fiction",
                "Mystery",
                "Thriller",
                "Romance",
                "Science-Fiction",
                "Fantasy",
                "Horror",
                "Historical Fiction",
                "Biography",
                "Autobiography",
                "Memoir",
                "Self Help",
                "Travel",
                "Business",
                "Cooking",
                "Art",
                "History",
                "Religion",
                "Romance",
                "Science",
                "Poetry",
                "Drama",
                "Comics",
                "Philosophy",
                "Kids"
            )
            var mIsBookGenreExpanded by remember {
                mutableStateOf(false)
            }
            var mBookGenre by remember {
                mutableStateOf(mBookGenreOptions[0])
            }
            val mBookAcquisitionMethodOptions = listOf(
                "Purchase",
                "Donation",
                "Exchange(Institutional)",
                "Exchange(Individual)",
                "Subscription Service",
                "InterLibrary",
                "Field Collection",
                "Gift Shop",
                "Complimentary Copy",
                "Government Deposit",
                "Digitization",
                "Cultural Exchange",
            )
            var mIsBookAcquisitionMethodExpanded by remember {
                mutableStateOf(false)
            }
            var mBookAcquisitionMethod by remember {
                mutableStateOf(mBookAcquisitionMethodOptions[0])
            }
            var mBookTitle by remember { mutableStateOf(TextFieldValue(bookTitle)) }
            var mBookAuthor by remember { mutableStateOf(TextFieldValue(bookAuthor)) }
            var mBookPrice by remember { mutableStateOf(TextFieldValue(bookPrice)) }
            var mBookPublisher by remember { mutableStateOf(TextFieldValue(bookPublisher)) }
            var mBookPublicationDate by remember { mutableStateOf(TextFieldValue(bookPublicationDate)) }
            var mBookShelfNumber by remember { mutableStateOf(TextFieldValue(bookShelfNumber)) }
            var mBookSynopsis by remember { mutableStateOf(TextFieldValue(bookSynopsis)) }
            var mBookEdition by remember { mutableStateOf(TextFieldValue(bookEdition)) }
            var mBookLanguage by remember { mutableStateOf(TextFieldValue(bookLanguage)) }
            var mBookNumberOfPages by remember { mutableStateOf(TextFieldValue(bookNumberOfPages)) }
            var mBookISBNNumber by remember { mutableStateOf(TextFieldValue(bookISBNNumber)) }
            var mBookYearOfPublication by remember {
                mutableStateOf(
                    TextFieldValue(
                        bookYearOfPublication
                    )
                )
            }

            Log.d("Firebase", "Book ID: $bookId")
            val currentDataRef =
                FirebaseDatabase.getInstance().getReference().child("Books/$bookId")
            val path = "Books/$bookId"
            Log.d("Firebase", "Database Reference Path: $path")
            Log.d("Firebase", "Fetching book with ID: $bookId")
            currentDataRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val book = snapshot.getValue(Books::class.java)
                        if (book != null) {
                            mBookTitle = TextFieldValue(book.bookTitle)
                            mBookAuthor = TextFieldValue(book.bookAuthor)
                            mBookYearOfPublication = TextFieldValue(book.bookYearOfPublication)
                            bookPrice = book.bookPrice
                            mBookISBNNumber = TextFieldValue(book.bookISBNNumber)
                            mBookPublisher = TextFieldValue(book.bookPublisher)
                            mBookPublicationDate = TextFieldValue(book.bookPublicationDate)
                            mBookGenre = book.bookGenre
                            mBookEdition = TextFieldValue(book.bookEdition)
                            mBookLanguage = TextFieldValue(book.bookLanguage)
                            mBookNumberOfPages = TextFieldValue(book.bookNumberOfPages)
                            mBookAcquisitionMethod = book.bookAcquisitionMethod
                            mBookCondition = book.bookCondition
                            mBookShelfNumber = TextFieldValue(book.bookShelfNumber)
                            mBookStatus = book.bookStatus
                            mBookSynopsis = TextFieldValue(book.bookSynopsis)
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

            OutlinedTextField(
                value = mBookTitle,
                onValueChange = { mBookTitle = it },
                label = { Text(text = "Book Title *") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Cyan,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Magenta,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = mBookAuthor,
                onValueChange = { mBookAuthor = it },
                label = { Text(text = "Book Author *") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Cyan,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Magenta,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = mBookPrice,
                onValueChange = { mBookPrice = it },
                label = { Text(text = "Book Price *") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Cyan,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Magenta,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = mBookPublisher,
                onValueChange = { mBookPublisher = it },
                label = { Text(text = "Book Publisher *") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Cyan,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Magenta,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = mBookPublicationDate,
                onValueChange = { mBookPublicationDate = it },
                label = { Text(text = "Book Publication Date *") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Cyan,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Magenta,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    )
                    .border(width = Dp.Hairline, color = Color.Black)
                    .background(color = Color.White)
            ) {
                Text(
                    text = "Book Condition:",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = Color.Magenta
                )
                ExposedDropdownMenuBox(
                    expanded = mIsOpen,
                    onExpandedChange = { mIsOpen = !mIsOpen }
                ) {
                    TextField(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(
                                start = 10.dp,
                                end = 10.dp,
                                top = 0.dp,
                                bottom = 0.dp
                            ),
                        value = mBookCondition,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = mIsOpen) },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Magenta,
                            unfocusedTextColor = Color.Red,
                            focusedContainerColor = Color.Cyan,
                            unfocusedContainerColor = Color.Green,
                            disabledContainerColor = Color.White,
                            focusedLabelColor = Color.Green,
                            unfocusedLabelColor = Color.Magenta
                        ),
                    )
                    ExposedDropdownMenu(
                        expanded = mIsOpen,
                        onDismissRequest = { mIsOpen = false }) {
                        mBookConditionOptions.forEachIndexed { index, text ->
                            DropdownMenuItem(
                                text = { Text(text = text) },
                                onClick = { mBookCondition = mBookConditionOptions[index] },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }

                }
            }
            Text(text = "Currently Selected: $mBookCondition")
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = mBookShelfNumber,
                onValueChange = { mBookShelfNumber = it },
                label = { Text(text = "Book Shelf Number *") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Cyan,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Magenta,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    )
                    .border(width = Dp.Hairline, color = Color.Black)
                    .background(color = Color.White)
            ) {
                Text(
                    text = "Book Status:",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = Color.Magenta
                )
                ExposedDropdownMenuBox(expanded = mIsExpanded,
                    onExpandedChange = { mIsExpanded = !mIsExpanded }
                ) {
                    TextField(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(
                                start = 10.dp,
                                end = 10.dp,
                                top = 0.dp,
                                bottom = 0.dp
                            ),
                        value = mBookStatus,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = mIsExpanded) },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Magenta,
                            unfocusedTextColor = Color.Red,
                            focusedContainerColor = Color.Cyan,
                            unfocusedContainerColor = Color.Green,
                            disabledContainerColor = Color.White,
                            focusedLabelColor = Color.Green,
                            unfocusedLabelColor = Color.Magenta
                        ),
                    )
                    ExposedDropdownMenu(
                        expanded = mIsExpanded,
                        onDismissRequest = { mIsExpanded = false }) {
                        mBookStatusOptions.forEachIndexed { index, text ->
                            DropdownMenuItem(
                                text = { Text(text = text) },
                                onClick = { mBookStatus = mBookStatusOptions[index] },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }

                }
            }
            Text(text = "Currently Selected: $mBookStatus")
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = mBookSynopsis,
                onValueChange = { mBookSynopsis = it },
                label = { Text(text = "Book Synopsis *") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Cyan,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Magenta,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = mBookEdition,
                onValueChange = { mBookEdition = it },
                label = { Text(text = "Book Edition *") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Cyan,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Magenta,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = mBookLanguage,
                onValueChange = { mBookLanguage = it },
                label = { Text(text = "Book Language *") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Cyan,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Magenta,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = mBookNumberOfPages,
                onValueChange = { mBookNumberOfPages = it },
                label = { Text(text = "Book Number Of Pages *") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Cyan,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Magenta,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = mBookISBNNumber,
                onValueChange = { mBookISBNNumber = it },
                label = { Text(text = "Book ISBN Number *") }, // international standard book number
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Cyan,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Magenta,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    )
                    .border(width = Dp.Hairline, color = Color.Black)
                    .background(color = Color.White)
            ) {
                Text(
                    text = "Book Genre:",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = Color.Magenta
                )
                ExposedDropdownMenuBox(expanded = mIsBookGenreExpanded,
                    onExpandedChange = { mIsBookGenreExpanded = !mIsBookGenreExpanded }
                ) {
                    TextField(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(
                                start = 10.dp,
                                end = 10.dp,
                                top = 0.dp,
                                bottom = 0.dp
                            ),
                        value = mBookGenre,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = mIsBookGenreExpanded) },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Magenta,
                            unfocusedTextColor = Color.Red,
                            focusedContainerColor = Color.Cyan,
                            unfocusedContainerColor = Color.Green,
                            disabledContainerColor = Color.White,
                            focusedLabelColor = Color.Green,
                            unfocusedLabelColor = Color.Magenta
                        ),
                    )
                    ExposedDropdownMenu(
                        expanded = mIsBookGenreExpanded,
                        onDismissRequest = { mIsBookGenreExpanded = false }) {
                        mBookGenreOptions.forEachIndexed { index, text ->
                            DropdownMenuItem(
                                text = { Text(text = text) },
                                onClick = { mBookGenre = mBookGenreOptions[index] },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }

                }
            }
            Text(text = "Currently Selected: $mBookGenre")
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    )
                    .border(width = Dp.Hairline, color = Color.Black)
                    .background(color = Color.White)
            ) {
                Text(
                    text = "Book Acquisition Method:",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    color = Color.Magenta
                )
                ExposedDropdownMenuBox(expanded = mIsBookAcquisitionMethodExpanded,
                    onExpandedChange = {
                        mIsBookAcquisitionMethodExpanded = !mIsBookAcquisitionMethodExpanded
                    }
                ) {
                    TextField(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(
                                start = 10.dp,
                                end = 10.dp,
                                top = 0.dp,
                                bottom = 0.dp
                            ),
                        value = mBookAcquisitionMethod,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = mIsBookAcquisitionMethodExpanded) },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Magenta,
                            unfocusedTextColor = Color.Red,
                            focusedContainerColor = Color.Cyan,
                            unfocusedContainerColor = Color.Green,
                            disabledContainerColor = Color.White,
                            focusedLabelColor = Color.Green,
                            unfocusedLabelColor = Color.Magenta
                        ),
                    )
                    ExposedDropdownMenu(
                        expanded = mIsBookAcquisitionMethodExpanded,
                        onDismissRequest = { mIsBookAcquisitionMethodExpanded = false }) {
                        mBookAcquisitionMethodOptions.forEachIndexed { index, text ->
                            DropdownMenuItem(
                                text = { Text(text = text) },
                                onClick = {
                                    mBookAcquisitionMethod = mBookAcquisitionMethodOptions[index]
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                            )
                        }
                    }

                }
            }
            Text(text = "Currently Selected: $mBookAcquisitionMethod")
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = mBookYearOfPublication,
                onValueChange = { mBookYearOfPublication = it },
                label = { Text(text = "Book Year Of Publication *") },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    unfocusedTextColor = Color.Cyan,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    focusedLabelColor = Color.Green,
                    unfocusedLabelColor = Color.Magenta,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 10.dp,
                        end = 10.dp,
                        bottom = 0.dp,
                        top = 0.dp
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            var currentBook by remember {
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
                        "",
                        ""
                    )
                )
            }

            val currentProductDataRef =
                FirebaseDatabase.getInstance().getReference().child("Books/$bookId")
            currentProductDataRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val book = snapshot.getValue(Books::class.java)
                    currentBook = book ?: Books(
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
                        "",
                        ""
                    )
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            })
//        DisplayProducts(
//            name =currentBook.name ,
//            price = currentBook.price,
//            quantity = currentBook.quantity,
//            id = currentBook.id
//        )

            ImageUploader(
                Modifier,
                context,
                navController,
                mBookTitle.text.trim(),
                mBookAuthor.text.trim(),
                mBookYearOfPublication.text.trim(),
                mBookPrice.text.trim(),
                mBookISBNNumber.text.trim(),
                mBookPublisher.text.trim(),
                mBookPublicationDate.text.trim(),
                mBookGenre.trim(),
                mBookEdition.text.trim(),
                mBookLanguage.text.trim(),
                mBookNumberOfPages.text.trim(),
                mBookAcquisitionMethod.trim(),
                mBookCondition.trim(),
                mBookShelfNumber.text.trim(),
                mBookStatus.trim(),
                mBookSynopsis.text.trim(),
                bookId
            )

            Button(
                onClick = { navController.navigate(ROUTE_BOOKS_HOME) },
                colors = ButtonDefaults.buttonColors(Color.Blue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    )
            ) {
                Text(
                    text = "Back to Home Screen",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }

        }
    }
}

@Composable
fun ImageUploader(
    modifier: Modifier = Modifier,
    context: Context,
    navController: NavHostController,
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
    bookId: String
) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = Modifier) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Selected image",
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 0.dp
                ))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    imagePicker.launch("image/*")
                }
            ) {
                Text(
                    text = "Select Image"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                val bookRepository = BooksViewModel(navController,context)
                bookRepository.updateBook(
                    bookId,
                    bookTitle,
                    bookAuthor,
                    bookYearOfPublication,
                    bookPrice,
                    bookISBNNumber,
                    bookPublisher,
                    bookPublicationDate,
                    bookGenre,
                    bookEdition,
                    bookLanguage,
                    bookNumberOfPages,
                    bookAcquisitionMethod,
                    bookCondition,
                    bookShelfNumber,
                    bookStatus,
                    bookSynopsis,
                    imageUri
                )

            }) {
                Text(text = "Upload")
            }
            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                navController.navigate(ROUTE_VIEW_BOOKS)
            },
            ) {
                Text(text = "view uploads")
            }

        }
    }
}
@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Edit Books Screen Preview"
)
@Composable
fun EditBooksScreenPreview(){
    ELibrariumTheme {
        EditBooksScreen(navController = rememberNavController(), bookId = "")
    }
}
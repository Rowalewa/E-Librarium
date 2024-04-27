@file:Suppress("DEPRECATION")

package com.example.e_librarium.ui.theme.screens.books

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.R
import com.example.e_librarium.data.BooksViewModel
import com.example.e_librarium.ui.theme.ELibrariumTheme
import kotlin.math.E

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBooksScreen(navController: NavHostController){
    val  bookStatus = listOf("Available", "Borrowed", "Reserved")
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedText by remember {
        mutableStateOf(bookStatus[0])
    }
    val  bookConditionOptions = listOf("New", "Used", "Damaged")
    var isOpen by remember {
        mutableStateOf(false)
    }
    var bookCondition by remember {
        mutableStateOf(bookConditionOptions[0])
    }
    val  bookGenreOptions = listOf(
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
    var isBookGenreExpanded by remember {
        mutableStateOf(false)
    }
    var bookGenre by remember {
        mutableStateOf(bookGenreOptions[0])
    }
    val  bookAcquisitionMethodOptions = listOf(
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
    var isBookAcquisitionMethodExpanded by remember {
        mutableStateOf(false)
    }
    var bookAcquisitionMethod by remember {
        mutableStateOf(bookAcquisitionMethodOptions[0])
    }
    val context = LocalContext.current
    var bookTitle by remember { mutableStateOf(TextFieldValue("")) }
    var bookAuthor by remember { mutableStateOf(TextFieldValue("")) }
    var bookPrice by remember { mutableStateOf(TextFieldValue("")) }
    var bookPublisher by remember { mutableStateOf(TextFieldValue("")) }
    var bookPublicationDate by remember { mutableStateOf(TextFieldValue("")) }
    var bookShelfNumber by remember { mutableStateOf(TextFieldValue("")) }
    var bookSynopsis by remember { mutableStateOf(TextFieldValue("")) }
    var bookEdition by remember { mutableStateOf(TextFieldValue("")) }
    var bookLanguage by remember { mutableStateOf(TextFieldValue("")) }
    var bookNumberOfPages by remember { mutableStateOf(TextFieldValue("")) }
    var bookISBNNumber by remember { mutableStateOf(TextFieldValue("")) }
    var bookYearOfPublication by remember { mutableStateOf(TextFieldValue("")) }
    Box {
        Image(painter = painterResource(id = R.drawable.add_books),
            contentDescription = "Add Books Background",
            modifier = Modifier.fillMaxSize()
        )
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(
                rememberScrollState(),
                enabled = true,
                reverseScrolling = true
            )
    ){
        Text(
            text = "Add Books",
            fontSize = 30.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        OutlinedTextField(
            value = bookTitle,
            onValueChange = { bookTitle = it },
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
            value = bookAuthor,
            onValueChange = { bookAuthor = it },
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
            value = bookPrice,
            onValueChange = { bookPrice = it },
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
            value = bookPublisher,
            onValueChange = { bookPublisher = it },
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
            value = bookPublicationDate,
            onValueChange = { bookPublicationDate = it },
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
        Row (
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
                .border(width = Dp.Hairline, color = Color.Black)
                .background(color = Color.White)
        ){
            Text(
                text = "Book Condition:",
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                color = Color.Magenta
            )
            ExposedDropdownMenuBox(
                expanded = isOpen,
                onExpandedChange = { isOpen = !isOpen }
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
                    value = bookCondition,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isOpen) },
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
                    expanded = isOpen,
                    onDismissRequest = { isOpen = false }) {
                    bookConditionOptions.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = { bookCondition = bookConditionOptions[index] },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }

            }
        }
        Text(text = "Currently Selected: $bookCondition")
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = bookShelfNumber,
            onValueChange = { bookShelfNumber = it },
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
        Row (
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
                .border(width = Dp.Hairline, color = Color.Black)
                .background(color = Color.White)
        ){
            Text(
                text = "Book Status:",
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                color = Color.Magenta
            )
            ExposedDropdownMenuBox(expanded = isExpanded,
                onExpandedChange = { isExpanded = !isExpanded }
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
                    value = selectedText,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
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
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }) {
                    bookStatus.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = { selectedText = bookStatus[index] },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }

            }
        }
        Text(text = "Currently Selected: $selectedText")
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = bookSynopsis,
            onValueChange = { bookSynopsis = it },
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
            value = bookEdition,
            onValueChange = { bookEdition = it },
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
            value = bookLanguage,
            onValueChange = { bookLanguage = it },
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
            value = bookNumberOfPages,
            onValueChange = { bookNumberOfPages = it },
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
            value = bookISBNNumber,
            onValueChange = { bookISBNNumber = it },
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
        Row (
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
                .border(width = Dp.Hairline, color = Color.Black)
                .background(color = Color.White)
        ){
            Text(
                text = "Book Genre:",
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                color = Color.Magenta
            )
            ExposedDropdownMenuBox(expanded = isBookGenreExpanded,
                onExpandedChange = { isBookGenreExpanded = !isBookGenreExpanded }
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
                    value = bookGenre,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isBookGenreExpanded) },
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
                    expanded = isBookGenreExpanded,
                    onDismissRequest = { isBookGenreExpanded = false }) {
                    bookGenreOptions.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = { bookGenre = bookGenreOptions[index] },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }

            }
        }
        Text(text = "Currently Selected: $bookGenre")
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
                .border(width = Dp.Hairline, color = Color.Black)
                .background(color = Color.White)
        ){
            Text(
                text = "Book Acquisition Method:",
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                color = Color.Magenta
            )
            ExposedDropdownMenuBox(expanded = isBookAcquisitionMethodExpanded,
                onExpandedChange = { isBookAcquisitionMethodExpanded = !isBookAcquisitionMethodExpanded }
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
                    value = bookAcquisitionMethod,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isBookAcquisitionMethodExpanded) },
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
                    expanded = isBookAcquisitionMethodExpanded,
                    onDismissRequest = { isBookAcquisitionMethodExpanded = false }) {
                    bookAcquisitionMethodOptions.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = { bookAcquisitionMethod = bookAcquisitionMethodOptions[index] },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }

            }
        }
        Text(text = "Currently Selected: $bookAcquisitionMethod")
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = bookYearOfPublication,
            onValueChange = { bookYearOfPublication = it },
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
        ImagePicker(
            Modifier,
            context,
            navController,
            bookTitle.text.trim(),
            bookAuthor.text.trim(),
            bookYearOfPublication.text.trim(),
            bookPrice.text.trim(),
            bookISBNNumber.text.trim(),
            bookPublisher.text.trim(),
            bookPublicationDate.text.trim(),
            bookGenre.trim(),
            bookEdition.text.trim(),
            bookLanguage.text.trim(),
            bookNumberOfPages.text.trim(),
            bookAcquisitionMethod.trim(),
            bookCondition.trim(),
            bookShelfNumber.text.trim(),
            selectedText.trim(),
            bookSynopsis.text.trim()
        )
    }
}
@Composable
fun ImagePicker(
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
                val productRepository = BooksViewModel(navController,context)
                productRepository.saveBook(
                    bookTitle,
                    bookAuthor,
                    bookCondition,
                    bookPrice,
                    bookISBNNumber,
                    bookPublisher,
                    bookPublicationDate,
                    bookGenre,
                    bookEdition,
                    bookLanguage,
                    bookNumberOfPages,
                    bookAcquisitionMethod,
                    bookYearOfPublication,
                    bookShelfNumber,
                    bookStatus,
                    bookSynopsis,
                    imageUri!!
                )

            }) {
                Text(text = "Upload")
            }
            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//

//                navController.navigate(ROUTE_VIEW_UPLOAD_SCREEN)

            }) {
                Text(text = "view uploads")
            }

        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Add Books Screen Preview"
)
@Composable
fun AddBooksScreenPreview(){
    ELibrariumTheme {
        AddBooksScreen(navController = rememberNavController())
    }
}
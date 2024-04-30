package com.example.e_librarium.ui.theme.screens.borrowing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.e_librarium.R
import com.example.e_librarium.data.AuthViewModel
import com.example.e_librarium.data.BooksViewModel
import com.example.e_librarium.models.BorrowingBook
import com.example.e_librarium.navigation.ROUTE_BORROW_BOOKS
import com.example.e_librarium.navigation.ROUTE_EDIT_BOOKS
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun BorrowHomeScreen(navController: NavHostController, clientId: String){
    val context = LocalContext.current
    val booksViewModel = remember { BooksViewModel(navController, context) }
    val (borrowedBooks, setBorrowedBooks) = remember { mutableStateOf<List<BorrowingBook>>(emptyList()) }

    LaunchedEffect(clientId) {
        booksViewModel.getBorrowedBooksForClient(clientId) { books ->
            setBorrowedBooks(books)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.borrow_home),
            contentDescription = "Borrow Home",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        Column {
            Button(
                onClick = {
                    val myClientLogout = AuthViewModel(navController, context)
                    myClientLogout.clientlogout()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Text(text = "Sign Out")
            }
            Button(
                onClick = {
                    navController.navigate(ROUTE_BORROW_BOOKS)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow
                )
            ) {
                Text(text = "Borrow Books Screen")
            }
            LazyColumn {
                items(borrowedBooks) { book ->
                    BookItems(
                        bookId = book.bookId
                    )
                }
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


@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Borrow Books Home Screen Preview"
)
@Composable
fun BorrowBooksHomeScreenPreview(){
    ELibrariumTheme {
        BorrowHomeScreen(navController = rememberNavController(), clientId = "")
    }
}
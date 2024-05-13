package com.example.e_librarium.ui.theme.screens.userManual

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_librarium.ui.theme.screens.books.StaffAppTopBar
import com.example.e_librarium.ui.theme.screens.books.StaffBottomAppBar
import com.example.e_librarium.ui.theme.screens.borrowing.ClientAppTopBar
import com.example.e_librarium.ui.theme.screens.borrowing.ClientBottomAppBar

@Composable
fun UserManualScreenStaff(navController: NavController, staffId: String) {
    Box{
        Column {
            Box (
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ){
                StaffAppTopBar(navController, staffId)
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState(), enabled = true, reverseScrolling = true)
            ) {
                Text(
                    text = "User Manual",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "1. Add Books: \n Use the Add book button to save books to firebase as Staff.",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "2. Search Books: \n Use the search bar to find specific books by title, author, or category. This can be as guest or logged in as clint or staff",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "3. Borrow Books:\n Choose client then you will see all available books which you can borrow after searching if the quantity is greater than 0. \n The client must also not have borrowed more than 3 books.",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "4. Return Books: \n Choose client then you will see all available books which you can return the borrowed one after searching.\n If the client will not have borrowed the book then there will be a message saying the client did not borrow the book",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "5. View Books: \n Use the View Books button to view available books.",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "6. View Clients: \n Use the View Clients button to show all the clients.",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "7. View Client Information:\n Click on Menu icon on top app bar to open your account page; your profile.",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "8. Edit Client Information:\n Click Edit menu on View Client Profile Screen. \n Here you can change password or profile picture.",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "9. View Staff Information:\n Click on Menu icon on top app bar to open your account page; your profile.",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "10. Edit Staff Information:\n Click Edit menu on View Staff Profile Screen. \n Here you can change password or profile picture.",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "11. View Borrowed Books:\n Click on view borrowed books screen as a client. \n Here you can see all the books that you have borrowed, the return date, borrow date and the book details such as title",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "12. Submit Feedback:\n Click on mail outline icon button on bottom app bar. \n This is for submitting your feedback with email optional but should you decide to fill it it should be similar to yours.\n We value your feedback",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "13. Staff Contacts:\n Click on call icon button on bottom app bar. \n A screen with the staff contact details is populated here.\n This is in case you need to contact any member of the staff.",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "14. End User Licence Agreement:\n Click on settings icon button on bottom app bar. \n A screen with buttons is populated here.\n Click the End User Licence Agreement(EULA) Button. \n This is a legal contract between the software developer and the user of the software that outlines the terms and conditions for using the software. EULA primarily governs the use of the software itself",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "15. Privacy Policy:\n Click on settings icon button on bottom app bar. \n A screen with buttons is populated here.\n It outlines how this app collects, uses, stores, and protects user data..",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "16. User Manual:\n Click on settings icon button on bottom app bar. \n A screen with buttons is populated here.\n Guides you on using the app.",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "16. About:\n Click on info icon button on bottom app bar. \n A screen with the app information is populated here.\n This is all about the app, features, functionalities, version e.t.c.",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(70.dp))
                // Add more instructions as needed
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


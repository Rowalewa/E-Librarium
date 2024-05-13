package com.example.e_librarium.ui.theme.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.ui.theme.ELibrariumTheme
import com.example.e_librarium.ui.theme.screens.admin.AdminAppTopBar
import com.example.e_librarium.ui.theme.screens.admin.AdminBottomAppBar
import com.example.e_librarium.ui.theme.screens.borrowing.ClientAppTopBar
import com.example.e_librarium.ui.theme.screens.borrowing.ClientBottomAppBar

@Composable
fun AboutScreenAdmin(navController: NavController, adminId: String) {
//    val scrollState = rememberScrollState()
//    val coroutineScope = rememberCoroutineScope()
//
//    DisposableEffect(Unit) {
//        coroutineScope.launch {
//            scrollState.animateScrollToItem(
//                scrollState.firstVisibleItemIndex + 1
//            )
//            delay(2000) // Adjust the delay based on your desired scroll speed
//        }
//        onDispose { }
//    }

    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState(),
                enabled = true,
                reverseScrolling = true
            )
            .background(color = Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AdminAppTopBar(navController, adminId)
        Text(
            text = "About",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Yellow,
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp
        )
        Text(
            text = "ELibrarium",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Blue
        )
        Text(
            text = "Version: \n 1.0",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Developer: \n Leslie Wanjala",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Cyan
        )
        Text(
            text = "Credits: Special thanks to: \n Emobilis Technology Training Institute \n Felix Kegode \n Pinterest \n Pexels \n Open AI \n Android Developers \n Google \n Firebase",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "License: \n MIT License",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Cyan
        )
        Text(
            text = "Contacts: \n lesliewanjala@gmail.com \n 0791589514",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Features: \n Borrowing books \n Returning Books \n Maintaining Saved Book Records \n Maintaining Borrowed Books Records \n Maintaining Client Accounts \n Creating User Accounts \n Viewing Books",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Cyan
        )
        Text(
            text = "Staff Privileges and Functions: \n Manage Books \n Manage Memberships \n Manage Borrowing \n Manage other Library Resources \n Interact with clients \n Provide Feedback",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Client Privileges and Functions: \n Search and Browse Books \n Borrow Memberships \n Renew Books \n View Account Information \n Interact with library staff \n Provide feedback",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Cyan
        )
        Text(
            text = "©2024 Elibrarium. All rights reserved.",
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AdminBottomAppBar(navController, adminId)
        }
        // Add more Text elements for version, developer info, credits, etc.
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AboutScreenAdminPreview(){
    ELibrariumTheme {
        AboutScreenAdmin(navController = rememberNavController(), adminId = "")
    }
}

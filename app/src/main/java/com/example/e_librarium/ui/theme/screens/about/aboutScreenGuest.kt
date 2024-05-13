package com.example.e_librarium.ui.theme.screens.about

import android.widget.Toast
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import com.example.e_librarium.data.AuthViewModel
import com.example.e_librarium.navigation.ROUTE_ADMIN_EDIT_HOME
import com.example.e_librarium.navigation.ROUTE_ADMIN_VIEW_ACCOUNT
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.R
import com.example.e_librarium.ui.theme.ELibrariumTheme
import com.example.e_librarium.ui.theme.screens.admin.AdminAppTopBar
import com.example.e_librarium.ui.theme.screens.admin.AdminBottomAppBar
import com.example.e_librarium.ui.theme.screens.borrowing.ClientAppTopBar
import com.example.e_librarium.ui.theme.screens.borrowing.ClientBottomAppBar
import com.example.e_librarium.ui.theme.screens.dashboard.DashTopBar

@Composable
fun AboutScreenGuest(navController: NavController) {
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

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
//        Image(painter = painterResource(id = R.drawable.admin_client_edit_screen),
//            contentDescription = "View Clients Image",
//            modifier = Modifier.matchParentSize(),
//            contentScale = ContentScale.FillBounds
//        )
        Column{
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                DashTopBar(navController)
            }
            Column(
                modifier = Modifier
                    .verticalScroll(
                        state = rememberScrollState(),
                        enabled = true,
                        reverseScrolling = true
                    )
                    .background(color = Color.Red)
                    .padding(bottom = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "About",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(color = Color.Black, shape = CutCornerShape(10.dp)),
                    textAlign = TextAlign.Center,
                    color = Color.Yellow,
                    fontFamily = FontFamily.Serif,
                    fontSize = 30.sp
                )
                Text(
                    text = "ELibrarium",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.Blue,
                    fontSize = 30.sp,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = "Version: \n 1.0",
                    modifier = Modifier
                        .padding(8.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSize = 20.sp
                )
                Text(
                    text = "Developer: \n Leslie Wanjala",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(color = Color.Cyan),
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
                Text(
                    text = "Credits: Special thanks to: \n Emobilis Technology Training Institute \n Felix Kegode \n Pinterest \n Pexels \n Open AI \n Android Developers \n Google \n Firebase",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(color = Color.Blue, shape = CutCornerShape(20.dp)),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = "License: \n MIT License",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(color = Color.Black),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = "Contacts: \n lesliewanjala@gmail.com \n 0791589514",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(color = Color.Green),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black
                )
                Text(
                    text = "Features: \n Borrowing books \n Returning Books \n Maintaining Saved Book Records \n Maintaining Borrowed Books Records \n Maintaining Client Accounts \n Creating User Accounts \n Viewing Books",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(color = Color.Yellow, shape = RoundedCornerShape(10.dp)),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = "Staff Privileges and Functions: \n Manage Books \n Manage Memberships \n Manage Borrowing \n Manage other Library Resources \n Interact with clients \n Provide Feedback",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(color = Color.Magenta),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black
                )
                Text(
                    text = "Client Privileges and Functions: \n Search and Browse Books \n Borrow Memberships \n Renew Books \n View Account Information \n Interact with library staff \n Provide feedback",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .background(color = Color.Cyan, shape = RoundedCornerShape(10.dp)),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif
                )
                Text(
                    text = "©2024 Elibrarium. All rights reserved.",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                // Add more Text elements for version, developer info, credits, etc.
            }
        }
        Column (
            modifier = Modifier.align(Alignment.BottomCenter)
        ){
            AdminBottomAppBar(navController, adminId = "")
        }
    }
}
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AboutScreenGuestPreview(){
    ELibrariumTheme {
        AboutScreenGuest(navController = rememberNavController())
    }
}

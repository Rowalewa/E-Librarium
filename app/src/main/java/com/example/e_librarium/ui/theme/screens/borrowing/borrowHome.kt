package com.example.e_librarium.ui.theme.screens.borrowing

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.e_librarium.R
import com.example.e_librarium.data.AuthViewModel
import com.example.e_librarium.navigation.ROUTE_BOOKS_HOME
import com.example.e_librarium.navigation.ROUTE_BORROW_HOME
import com.example.e_librarium.navigation.ROUTE_CLIENT_FEEDBACK
import com.example.e_librarium.navigation.ROUTE_VIEW_ALL_BOOKS_CLIENT
import com.example.e_librarium.navigation.ROUTE_VIEW_BOOKS_GUEST
import com.example.e_librarium.navigation.ROUTE_VIEW_BORROWED_BOOKS
import com.example.e_librarium.navigation.ROUTE_VIEW_CLIENT_INFO
import com.example.e_librarium.navigation.ROUTE_VIEW_STAFF_INFO
import com.google.firebase.auth.FirebaseAuth

@Composable
fun BorrowHomeScreen(navController: NavHostController, clientId: String){
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.borrow_home),
            contentDescription = "Borrow Home",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        Column {
            ClientAppTopBar(navController, clientId)
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
                    navController.navigate("$ROUTE_VIEW_BORROWED_BOOKS/$clientId")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow
                )
            ) {
                Text(text = "View My Borrowed Books")
            }
            Button(
                onClick = {
                    navController.navigate("$ROUTE_VIEW_ALL_BOOKS_CLIENT/$clientId")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow
                )
            ) {
                Text(text = "View Library Books")
            }
            Button(
                onClick = {
                    navController.navigate("$ROUTE_CLIENT_FEEDBACK/$clientId")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow
                )
            ) {
                Text(text = "Submit Feedback")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientAppTopBar(navController: NavController, clientId: String){
    val context = LocalContext.current
    var expanded by remember {
        mutableStateOf(false)
    }
    val client = FirebaseAuth.getInstance().currentUser
    val clientProfilePictureUrl = client?.photoUrl?.toString()
    TopAppBar(
        title = {
            Text(
                text = "E-Librarium",
                fontFamily = FontFamily.Serif,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Green
            )
        },
        navigationIcon ={
            IconButton(onClick = {
                navController.navigate("$ROUTE_BORROW_HOME/$clientId")
                Toast.makeText(context, "You are at Home Screen", Toast.LENGTH_LONG).show()}
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription ="Home",
                    tint = Color.Yellow
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Red,
            titleContentColor = Color.Black,
            navigationIconContentColor = Color.White
        ),
        actions = {
            IconButton(onClick = {
                navController.navigateUp()
            }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription ="Back",
                    tint = Color.Magenta
                )
            }
            IconButton(
                onClick = { expanded = true }
            ) {
                clientProfilePictureUrl?.let {
                    Image(
                        painter = rememberAsyncImagePainter(it),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                } ?: Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "menu",
                    tint = Color.White
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "My Account") },
                    onClick = {
                        Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                        navController.navigate("$ROUTE_VIEW_CLIENT_INFO/$clientId")
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Sign Out") },
                    onClick = {
                        val myLogout = AuthViewModel(navController, context)
                        myLogout.clientlogout()
                        Toast.makeText(context, "You have clicked sign out option: $clientId", Toast.LENGTH_LONG).show()
                    }
                )
                // Add more DropdownMenuItem for other account options
            }
        }

    )

}

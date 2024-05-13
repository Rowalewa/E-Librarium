package com.example.e_librarium.ui.theme.screens.admin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import coil.compose.rememberAsyncImagePainter
import com.example.e_librarium.R
import com.example.e_librarium.data.AuthViewModel
import com.example.e_librarium.navigation.ROUTE_ABOUT_SCREEN_ADMIN
import com.example.e_librarium.navigation.ROUTE_ABOUT_SCREEN_STAFF
import com.example.e_librarium.navigation.ROUTE_ADMIN_CLIENT_EDIT
import com.example.e_librarium.navigation.ROUTE_ADMIN_EDIT_HOME
import com.example.e_librarium.navigation.ROUTE_ADMIN_FEEDBACK
import com.example.e_librarium.navigation.ROUTE_ADMIN_STAFF_EDIT
import com.example.e_librarium.navigation.ROUTE_ADMIN_VIEW_ACCOUNT
import com.example.e_librarium.navigation.ROUTE_BOOKS_HOME
import com.example.e_librarium.navigation.ROUTE_EULA_ADMIN
import com.example.e_librarium.navigation.ROUTE_EULA_STAFF
import com.example.e_librarium.navigation.ROUTE_PRIVACY_POLICY_ADMIN
import com.example.e_librarium.navigation.ROUTE_PRIVACY_POLICY_STAFF
import com.example.e_librarium.navigation.ROUTE_STAFF_CONTACT_AS_ADMIN
import com.example.e_librarium.navigation.ROUTE_STAFF_CONTACT_AS_STAFF
import com.example.e_librarium.navigation.ROUTE_STAFF_FEEDBACK
import com.example.e_librarium.navigation.ROUTE_USER_MANUAL_ADMIN
import com.example.e_librarium.navigation.ROUTE_USER_MANUAL_STAFF
import com.example.e_librarium.navigation.ROUTE_VIEW_STAFF_INFO
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AdminEditHome(navController: NavController, adminId: String){
    Box (
        modifier = Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.admin_edit_home_screen),
            contentDescription = "View Clients Image",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AdminAppTopBar(navController, adminId)
            Text(
                text = "ACTIONS",
                color = Color.Red,
                fontSize = 30.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(15.dp))
            Card (
                colors = CardDefaults.cardColors(Color.Blue)
            ){
                Image(
                    painter = painterResource(id = R.drawable.staff_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                Button(onClick = { navController.navigate("$ROUTE_ADMIN_STAFF_EDIT/$adminId") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Edit Staff")
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Card (
                colors = CardDefaults.cardColors(Color.Red)
            ){
                Image(
                    painter = painterResource(id = R.drawable.client_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                Button(onClick = { navController.navigate("$ROUTE_ADMIN_CLIENT_EDIT/$adminId") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Edit Client")
                }
            }
        }
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            AdminBottomAppBar(navController, adminId)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAppTopBar(navController: NavController, adminId: String){
    val context = LocalContext.current
    var expanded by remember {
        mutableStateOf(false)
    }
    val staff = FirebaseAuth.getInstance().currentUser
    val staffProfilePictureUrl = staff?.photoUrl?.toString()
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
                navController.navigate("$ROUTE_ADMIN_EDIT_HOME/$adminId")
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
            containerColor = Color.Blue,
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
                staffProfilePictureUrl?.let {
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
                        navController.navigate("$ROUTE_ADMIN_VIEW_ACCOUNT/$adminId")
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Sign Out") },
                    onClick = {
                        val myLogout = AuthViewModel(navController, context)
                        myLogout.adminLogout()
                    }
                )
                // Add more DropdownMenuItem for other account options
            }
        }

    )
}
@Composable
fun AdminBottomAppBar(navController: NavController, adminId: String){
    var expanded by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    BottomAppBar(
        actions = {
            IconButton(onClick = { navController.navigate("$ROUTE_ADMIN_FEEDBACK/$adminId") }) {
                Icon(
                    Icons.Filled.MailOutline,
                    contentDescription = "Feedback"
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            IconButton(onClick = { navController.navigate("$ROUTE_ABOUT_SCREEN_ADMIN/$adminId") }) {
                Icon(
                    Icons.Filled.Info,
                    contentDescription = "About",
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            IconButton(onClick = { navController.navigate("$ROUTE_STAFF_CONTACT_AS_ADMIN/$adminId") }) {
                Icon(
                    Icons.Filled.Phone,
                    contentDescription = "Phone Numbers",
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { expanded = true },
                containerColor = Color.Black,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Localized description",
                    tint = Color.Yellow
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Privacy Policy") },
                        onClick = {
                            Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                            navController.navigate("$ROUTE_PRIVACY_POLICY_ADMIN/$adminId")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "EULA") },
                        onClick = {
                            Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                            navController.navigate("$ROUTE_EULA_ADMIN/$adminId")

                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "User Manual") },
                        onClick = {
                            Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
                            navController.navigate("$ROUTE_USER_MANUAL_ADMIN/$adminId")
                        }
                    )
                    // Add more DropdownMenuItem for other account options
                }
            }
        }
    )
}
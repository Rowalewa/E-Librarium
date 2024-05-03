package com.example.e_librarium.ui.theme.screens.borrowing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.example.e_librarium.R
import com.example.e_librarium.data.AuthViewModel
import com.example.e_librarium.navigation.ROUTE_VIEW_BORROWED_BOOKS

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
            OutlinedTextField(
                value = clientId,
                onValueChange = {}
            )
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
                Text(text = "View Borrowed Books Screen")
            }
        }
    }
}

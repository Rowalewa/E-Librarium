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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.e_librarium.R
import com.example.e_librarium.data.AuthViewModel
import com.example.e_librarium.models.Books
import com.example.e_librarium.models.Clients
import com.example.e_librarium.navigation.ROUTE_BOOKS_HOME
import com.example.e_librarium.navigation.ROUTE_BORROW_BOOKS
import com.example.e_librarium.navigation.ROUTE_VIEW_BOOKS

@Composable
fun ViewClientsScreen(navController: NavHostController){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = R.drawable.view_clients),
            contentDescription = "View Clients Image",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val context = LocalContext.current
            val clientsRepository = AuthViewModel(navController, context)
            val emptyClientState =
                remember { mutableStateOf(Clients("", "", "", "", "", "", "", "", "")) }
            val emptyClientListState = remember { mutableStateListOf<Clients>() }

            val books = clientsRepository.viewClients(emptyClientState, emptyClientListState)


            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "CLIENTS",
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(10.dp))

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

                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(
                        start = 0.dp,
                        end = 0.dp,
                        bottom = 20.dp,
                        top = 0.dp
                    )
                ) {
                    items(books) {
                        ClientInstance(
                            fullName = it.fullName,
                            gender = it.gender,
                            maritalStatus = it.maritalStatus,
                            phoneNumber = it.phoneNumber,
                            dateOfBirth = it.dateOfBirth,
                            email = it.email,
                            clientId = it.clientid,
                            clientProfilePictureUrl = it.clientProfilePictureUrl,
                            navController = navController,
                            clientRepository = clientsRepository
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ClientInstance(
    fullName: String,
    gender: String,
    maritalStatus: String,
    phoneNumber: String,
    dateOfBirth: String,
    email: String,
    clientId: String,
    clientProfilePictureUrl: String,
    navController: NavHostController,
    clientRepository: AuthViewModel
) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = 20.dp,
            top = 0.dp,
            end = 20.dp,
            bottom = 0.dp
        )
        .clip(shape = CutCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color.Green)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(clientProfilePictureUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(400.dp)
                    .padding(18.dp)
                    .clip(shape = CircleShape)
            )
            Text(
                text = "Client Name: $fullName",
                color = Color.Black
            )
            Text(
                text = "Client Gender: $gender",
                color = Color.Black
            )
            Text(
                text = "Client Marital Status: $maritalStatus",
                color = Color.Black
            )
            Text(
                text = "Client Phone Number: $phoneNumber",
                color = Color.Black
            )
            Text(
                text = "Client Date of Birth: $dateOfBirth",
                color = Color.Black
            )
            Text(
                text = "Client Email: $email",
                color = Color.Black
            )
            Text(
                text = "Client Id: $clientId",
                color = Color.Black
            )
            Row(
                modifier = Modifier.background(color = Color.Yellow)
            ) {
                Button(
                    onClick = {
                        clientRepository.deleteClient(clientId)
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
                        navController.navigate(/*"$*/ROUTE_VIEW_BOOKS/*$clientId"*/)
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
                    Text(text = "Borrow")
                }
            }

            //----------------------Check on this one--------------------//
            Button(
                onClick = {
                    navController.navigate("$ROUTE_BORROW_BOOKS/$clientId")
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(
                        start = 0.dp,
                        end = 20.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    ),
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text(text = "Borrow Books")
            }
        }
    }
    Spacer(modifier = Modifier.height(40.dp))
}
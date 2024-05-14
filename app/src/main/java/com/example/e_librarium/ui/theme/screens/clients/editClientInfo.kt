@file:Suppress("DEPRECATION")

package com.example.e_librarium.ui.theme.screens.clients

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.e_librarium.R
import com.example.e_librarium.data.AuthViewModel
import com.example.e_librarium.models.Clients
import com.example.e_librarium.ui.theme.screens.borrowing.ClientAppTopBar
import com.example.e_librarium.ui.theme.screens.borrowing.ClientBottomAppBar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun EditClientInfo(navController: NavHostController, clientId: String){
    val context = LocalContext.current
    val fullName by remember {
        mutableStateOf("")
    }
    val gender by remember {
        mutableStateOf("")
    }
    val maritalStatus by remember {
        mutableStateOf("")
    }
    val phoneNumber by remember {
        mutableStateOf("")
    }
    val dateOfBirth by remember {
        mutableStateOf("")
    }
    val email by remember {
        mutableStateOf("")
    }
    var pass by remember {
        mutableStateOf("")
    }
    var confpass by remember {
        mutableStateOf("")
    }
    val clientStatus by remember {
        mutableStateOf("")
    }
    val fine by remember {
        mutableStateOf("")
    }
    var mFullName by remember {
        mutableStateOf(TextFieldValue(fullName))
    }
    var mGender by remember {
        mutableStateOf(TextFieldValue(gender))
    }
    var mMaritalStatus by remember {
        mutableStateOf(TextFieldValue(maritalStatus))
    }
    var mPhoneNumber by remember {
        mutableStateOf(TextFieldValue(phoneNumber))
    }
    var mDateOfBirth by remember {
        mutableStateOf(TextFieldValue(dateOfBirth))
    }
    var mEmail by remember {
        mutableStateOf(TextFieldValue(email))
    }
    var mPass by remember {
        mutableStateOf(TextFieldValue(pass))
    }
    var mClientStatus by remember {
        mutableStateOf(TextFieldValue(clientStatus))
    }
    var clientFine by remember {
        mutableStateOf(TextFieldValue(fine))
    }
    val currentDataRef = FirebaseDatabase.getInstance().getReference().child("Client/$clientId")
    currentDataRef.addValueEventListener(object: ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val client = snapshot.getValue(Clients::class.java)
            mFullName = TextFieldValue(client!!.fullName)
            mGender = TextFieldValue(client.gender)
            mMaritalStatus = TextFieldValue(client.maritalStatus)
            mPhoneNumber = TextFieldValue(client.phoneNumber)
            mDateOfBirth = TextFieldValue(client.dateOfBirth)
            mEmail = TextFieldValue(client.email)
            pass = client.pass
            mClientStatus = TextFieldValue(client.clientStatus)
            clientFine = TextFieldValue(client.fine.toString())
        }

        override fun onCancelled(error: DatabaseError) {
            Toast.makeText(context,error.message, Toast.LENGTH_SHORT).show()
        }
    } )
    Box{
        Image(
            painter = painterResource(id = R.drawable.edit_client_info),
            contentDescription = "View Clients Image",
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        Column{
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                ClientAppTopBar(navController, clientId)
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = mEmail,
                    onValueChange = { mEmail = it },
                    label = { Text(text = "Email Address") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                OutlinedTextField(
                    value = mPass,
                    onValueChange = { mPass = it },
                    label = { Text(text = "Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                OutlinedTextField(
                    value = confpass,
                    onValueChange = { confpass = it },
                    label = { Text(text = "Confirm Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                ClientUploader(
                    context = context,
                    navController = navController,
                    fullName = mFullName.text.trim(),
                    gender = mGender.text.trim(),
                    maritalStatus = mMaritalStatus.text.trim(),
                    phoneNumber = mPhoneNumber.text.trim(),
                    dateOfBirth = mDateOfBirth.text.trim(),
                    email = mEmail.text.trim(),
                    pass = mPass.text.trim(),
                    confpass = confpass.trim(),
                    clientStatus = mClientStatus.text.trim(),
                    clientId = clientId,
                    fine = clientFine.text.trim().toDouble()
                )
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            ClientBottomAppBar(navController, clientId)
        }
    }
}

@Composable
fun ClientUploader(
    modifier: Modifier = Modifier,
    context: Context,
    navController: NavHostController,
    fullName: String,
    gender: String,
    maritalStatus: String,
    phoneNumber: String,
    dateOfBirth: String,
    email: String,
    pass: String,
    confpass: String,
    clientStatus: String,
    clientId: String,
    fine: Double
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
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Selected image",
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
            )
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
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Change Profile Picture"
                )
            }
            Button(onClick = {
                //-----------WRITE THE UPLOAD LOGIC HERE---------------//
                val clientRepository = AuthViewModel(navController, context)
                clientRepository.updateClient(
                    fullName,
                    gender,
                    maritalStatus,
                    phoneNumber,
                    dateOfBirth,
                    email,
                    pass,
                    confpass,
                    clientStatus,
                    clientId,
                    imageUri,
                    fine
                )

            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 7.dp,
                        end = 7.dp,
                        top = 2.dp,
                        bottom = 0.dp
                    )
            ) {
                Text(text = "Update Changes")
            }

        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}
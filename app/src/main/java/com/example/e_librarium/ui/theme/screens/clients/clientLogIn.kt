package com.example.e_librarium.ui.theme.screens.clients

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.data.AuthViewModel
import com.example.e_librarium.navigation.ROUTE_CLIENT_REGISTER
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun ClientLogInScreen(navController: NavController){
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    val context= LocalContext.current
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ){
        Text(
            text = "PLEASE LOG IN HERE CLIENT",
            fontSize = 30.sp,
            color = Color.Red,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value =email ,
            onValueChange = {email=it},
            label = { Text(text = "Enter Email") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Blue,
                unfocusedTextColor = Color.Red,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            ),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value =pass , onValueChange = {pass=it},
            label = { Text(text = "Enter Password") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Blue,
                unfocusedTextColor = Color.Red,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            val myLogin = AuthViewModel(navController, context)
            myLogin.clientlogin(
                email.text.trim(),
                pass.text.trim())

        }, modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 0.dp,
                bottom = 0.dp
            )) {
            Text(text = "Log In",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.Magenta
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Don't have account? Click to Register",
            color = Color.White
        )
        Button(onClick = {
            navController.navigate(ROUTE_CLIENT_REGISTER)
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 0.dp,
                bottom = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(Color.Blue)) {
            Text(text = "Register",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.Magenta
            )
        }

    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Client LogIn Screen Preview"
)
@Composable
fun ClientLogInScreenPreview(){
    ELibrariumTheme {
        ClientLogInScreen(navController = rememberNavController())
    }
}
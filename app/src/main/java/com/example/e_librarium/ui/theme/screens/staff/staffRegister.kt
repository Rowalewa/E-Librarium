package com.example.e_librarium.ui.theme.screens.staff

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.data.AuthViewModel
import com.example.e_librarium.navigation.ROUTE_STAFF_LOGIN
import com.example.e_librarium.ui.theme.ELibrariumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaffRegisterScreen(navController: NavController){
    var fullName by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val genderOptions = listOf("Male", "Female", "Prefer Not to Say")
    var isGenderExpanded by remember {
        mutableStateOf(false)
    }
    var gender by remember {
        mutableStateOf(genderOptions[0])
    }
    val marriageStatusOptions = listOf("Single", "Married", "Divorced", "Widowed")
    var isMarriageStatusExpanded by remember {
        mutableStateOf(false)
    }
    var marriageStatus by remember {
        mutableStateOf(marriageStatusOptions[0])
    }
    var phoneNumber by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var email by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var pass by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var confpass by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val context = LocalContext.current

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Text(
            text = " REGISTER ",
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Blue,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(color = Color.Red, shape = CutCornerShape(10.dp))
        )
        OutlinedTextField(
            value = fullName,
            onValueChange = {fullName = it},
            label = { Text(
                text = "Enter Your Full Name"
            )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
                .border(width = Dp.Hairline, color = Color.Black)
                .background(color = Color.White)
        ){
            Text(
                text = "Gender:",
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                color = Color.Black
            )
            ExposedDropdownMenuBox(
                expanded = isGenderExpanded,
                onExpandedChange = { isGenderExpanded = !isGenderExpanded }
            ) {
                TextField(
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 0.dp,
                            bottom = 0.dp
                        ),
                    value = gender,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isGenderExpanded) },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Magenta,
                        unfocusedTextColor = Color.Red,
                        focusedContainerColor = Color.Cyan,
                        unfocusedContainerColor = Color.Green,
                        disabledContainerColor = Color.White,
                        focusedLabelColor = Color.Green,
                        unfocusedLabelColor = Color.Magenta
                    ),
                )
                ExposedDropdownMenu(
                    expanded = isGenderExpanded,
                    onDismissRequest = { isGenderExpanded = false }) {
                    genderOptions.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = { gender = genderOptions[index] },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }

            }
        }
        Text(text = "Currently Selected: $gender")
        Spacer(modifier = Modifier.height(10.dp))
        Row (
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                    top = 0.dp,
                    bottom = 0.dp
                )
                .border(width = Dp.Hairline, color = Color.Black)
                .background(color = Color.White)
        ){
            Text(
                text = "Marriage Status:",
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                color = Color.Black
            )
            ExposedDropdownMenuBox(
                expanded = isMarriageStatusExpanded,
                onExpandedChange = { isMarriageStatusExpanded = !isMarriageStatusExpanded }
            ) {
                TextField(
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 0.dp,
                            bottom = 0.dp
                        ),
                    value = marriageStatus,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMarriageStatusExpanded) },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Magenta,
                        unfocusedTextColor = Color.Red,
                        focusedContainerColor = Color.Cyan,
                        unfocusedContainerColor = Color.Green,
                        disabledContainerColor = Color.White,
                        focusedLabelColor = Color.Green,
                        unfocusedLabelColor = Color.Magenta
                    ),
                )
                ExposedDropdownMenu(
                    expanded = isMarriageStatusExpanded,
                    onDismissRequest = { isMarriageStatusExpanded = false }) {
                    marriageStatusOptions.forEachIndexed { index, text ->
                        DropdownMenuItem(
                            text = { Text(text = text) },
                            onClick = { marriageStatus = marriageStatusOptions[index] },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                        )
                    }
                }

            }
        }
        Text(text = "Currently Selected: $marriageStatus")
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {phoneNumber = it},
            label = { Text(
                text = "Enter Your Phone Number"
            )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(
                text = "Enter Email Address"
            )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = pass,
            onValueChange = {pass = it},
            label = { Text(
                text = "Enter Password"
            )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = confpass,
            onValueChange = {confpass = it},
            label = { Text(
                text = "Confirm Password"
            )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Button(onClick = {
            val myRegister = AuthViewModel(navController, context)
            myRegister.staffsignup(
                fullName.text.trim(),
                gender.trim(),
                marriageStatus.trim(),
                phoneNumber.text.trim(),
                email.text.trim(),
                pass.text.trim(),
                confpass.text.trim()
            ) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 0.dp
                ),
            colors = ButtonDefaults.buttonColors(Color.Cyan)) {
            Text(
                text ="Register",
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif
            )

        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Already have an account?")
        Button(onClick = { navController.navigate(ROUTE_STAFF_LOGIN) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 0.dp
                ),
            colors = ButtonDefaults.buttonColors(Color.Cyan)) {
            Text(
                text ="Log In",
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif
            )

        }
    }

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Staff Register Screen Preview"
)
@Composable
fun StaffRegisterPreview(){
    ELibrariumTheme {
        StaffRegisterScreen(navController = rememberNavController())
    }
}
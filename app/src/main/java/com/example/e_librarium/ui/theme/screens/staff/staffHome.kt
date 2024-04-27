package com.example.e_librarium.ui.theme.screens.staff

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.R
import com.example.e_librarium.navigation.ROUTE_CLIENT_LOGIN
import com.example.e_librarium.navigation.ROUTE_CLIENT_REGISTER
import com.example.e_librarium.navigation.ROUTE_HOME
import com.example.e_librarium.navigation.ROUTE_STAFF_LOGIN
import com.example.e_librarium.navigation.ROUTE_STAFF_REGISTER
import com.example.e_librarium.ui.theme.ELibrariumTheme
import java.util.Calendar

@Composable
fun StaffHomeScreen(navController: NavController){
//    val currentTime = Calendar.getInstance().time
//    val currentHour = currentTime.hours
//    var greeting = when (currentHour){
//        in 0..11 -> "Good Morning"
//        in 12..17 -> "Good afternoon"
//        else -> "Good evening!"
//    }
    Box {
        Image(painter = painterResource(id = R.drawable.curved_wallpaper),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        Spacer(modifier = Modifier.height(20.dp))
//        TextField(value = greeting,
//            onValueChange = {greeting=it},
//            enabled = true,
//            readOnly = true,
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//                .wrapContentWidth(),
//
//        )
        Spacer(modifier = Modifier.height(20.dp))
        Box {
            Image(
                painter = painterResource(id = R.drawable.library_desk),
                contentDescription = null,
                modifier = Modifier.height(400.dp))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { navController.navigate(ROUTE_STAFF_LOGIN) },
            colors = ButtonDefaults.buttonColors(Color.Yellow),
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
                text = "Log In",
                color = Color.Red,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif

            )
        }
        Button(onClick = { navController.navigate(ROUTE_STAFF_REGISTER) },
            colors = ButtonDefaults.buttonColors(Color.Magenta),
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
                text = "Sign Up",
                color = Color.Black,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }
        Button(onClick = { navController.navigate(ROUTE_HOME) },
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
                color = Color.Green,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Staff Home Screen Preview"
)
@Composable
fun StaffHomeScreenPreview(){
    ELibrariumTheme {
        StaffHomeScreen(navController = rememberNavController())
    }
}
package com.example.e_librarium.ui.theme.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.navigation.ROUTE_CLIENT_LOGIN
import com.example.e_librarium.navigation.ROUTE_CLIENT_REGISTER
import com.example.e_librarium.navigation.ROUTE_STAFF_LOGIN
import com.example.e_librarium.navigation.ROUTE_STAFF_REGISTER
import com.example.e_librarium.ui.theme.ELibrariumTheme

@Composable
fun HomeScreen(navController: NavController){
    Column {
        Button(onClick = { navController.navigate(ROUTE_STAFF_REGISTER) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Staff Register")
        }
        Button(onClick = { navController.navigate(ROUTE_STAFF_LOGIN) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Staff LogIn")
        }
        Button(onClick = { navController.navigate(ROUTE_CLIENT_REGISTER) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Client Register")
        }
        Button(onClick = { navController.navigate(ROUTE_CLIENT_LOGIN) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Client LogIn")
        }
    }

}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Home Screen Preview"
)
@Composable
fun HomeScreenPreview(){
    ELibrariumTheme {
        HomeScreen(navController = rememberNavController())
    }
}
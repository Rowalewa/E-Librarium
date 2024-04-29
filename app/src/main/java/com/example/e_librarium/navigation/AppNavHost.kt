package com.example.e_librarium.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_librarium.ui.theme.screens.books.AddBooksScreen
import com.example.e_librarium.ui.theme.screens.books.BooksHomeScreen
import com.example.e_librarium.ui.theme.screens.books.EditBooksScreen
import com.example.e_librarium.ui.theme.screens.books.ViewBooksScreen
import com.example.e_librarium.ui.theme.screens.borrowing.BorrowBooksScreen
import com.example.e_librarium.ui.theme.screens.borrowing.BorrowHomeScreen
import com.example.e_librarium.ui.theme.screens.borrowing.ViewClientsScreen
import com.example.e_librarium.ui.theme.screens.clients.ClientHomeScreen
import com.example.e_librarium.ui.theme.screens.clients.ClientLogInScreen
import com.example.e_librarium.ui.theme.screens.clients.ClientRegisterScreen
import com.example.e_librarium.ui.theme.screens.home.HomeScreen
import com.example.e_librarium.ui.theme.screens.returning.ReturnBooksScreen
import com.example.e_librarium.ui.theme.screens.returning.ReturningHomeScreen
import com.example.e_librarium.ui.theme.screens.staff.StaffHomeScreen
import com.example.e_librarium.ui.theme.screens.staff.StaffLogInScreen
import com.example.e_librarium.ui.theme.screens.staff.StaffRegisterScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController = rememberNavController(),
               startDestination: String = ROUTE_HOME
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = startDestination
    ){
        composable(ROUTE_ADD_BOOKS){
            AddBooksScreen(navController)
        }
        composable(ROUTE_BOOKS_HOME){
            BooksHomeScreen(navController)
        }
        composable("$ROUTE_EDIT_BOOKS/{bookId}"){passedData ->
            EditBooksScreen(navController, passedData.arguments?.getString("bookId")!!)  // need for edit
        }
        composable(ROUTE_VIEW_BOOKS){
            ViewBooksScreen(navController)  // need for edit
        }
        composable(ROUTE_BORROW_BOOKS){
            BorrowBooksScreen(navController)  // need for edit
        }
        composable("$ROUTE_BORROW_HOME/{clientId}"){passedData ->
            BorrowHomeScreen(navController, passedData.arguments?.getString("clientId")!!)
        }
        composable(ROUTE_VIEW_CLIENTS){
            ViewClientsScreen(navController)  // need for edit
        }
        composable(ROUTE_CLIENT_LOGIN){
            ClientLogInScreen(navController)
        }
        composable(ROUTE_CLIENT_REGISTER){
            ClientRegisterScreen(navController)
        }
        composable(ROUTE_CLIENT_HOME){
            ClientHomeScreen(navController)
        }
        composable(ROUTE_HOME){
            HomeScreen(navController)
        }
        composable(ROUTE_RETURN_BOOKS){
            ReturnBooksScreen(navController)  //need for edit
        }
        composable(ROUTE_RETURN_HOME){
            ReturningHomeScreen(navController)
        }
        composable(ROUTE_STAFF_HOME){
            StaffHomeScreen(navController)
        }
        composable(ROUTE_STAFF_REGISTER){
            StaffRegisterScreen(navController)
        }
        composable(ROUTE_STAFF_LOGIN){
            StaffLogInScreen(navController)
        }

    }
}
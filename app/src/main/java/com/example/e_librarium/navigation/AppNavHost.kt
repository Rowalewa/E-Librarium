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
import com.example.e_librarium.ui.theme.screens.books.ViewAllBooksScreen
import com.example.e_librarium.ui.theme.screens.books.ViewBooksScreen
import com.example.e_librarium.ui.theme.screens.borrowing.BorrowBooksScreen
import com.example.e_librarium.ui.theme.screens.borrowing.BorrowHomeScreen
import com.example.e_librarium.ui.theme.screens.borrowing.ViewClientsScreen
import com.example.e_librarium.ui.theme.screens.clients.ClientHomeScreen
import com.example.e_librarium.ui.theme.screens.clients.ClientLogInScreen
import com.example.e_librarium.ui.theme.screens.clients.ClientRegisterScreen
import com.example.e_librarium.ui.theme.screens.clients.EditClientInfo
import com.example.e_librarium.ui.theme.screens.clients.ViewBorrowedBooks
import com.example.e_librarium.ui.theme.screens.clients.ViewClientInfo
import com.example.e_librarium.ui.theme.screens.home.HomeScreen
import com.example.e_librarium.ui.theme.screens.home.ViewBooksGuest
import com.example.e_librarium.ui.theme.screens.returning.ReturnBooksScreen
import com.example.e_librarium.ui.theme.screens.returning.ReturningHomeScreen
import com.example.e_librarium.ui.theme.screens.staff.EditStaffInfo
import com.example.e_librarium.ui.theme.screens.staff.StaffHomeScreen
import com.example.e_librarium.ui.theme.screens.staff.StaffLogInScreen
import com.example.e_librarium.ui.theme.screens.staff.StaffRegisterScreen
import com.example.e_librarium.ui.theme.screens.staff.ViewStaffInfo

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
        composable("$ROUTE_ADD_BOOKS/{staffId}"){passedData->
            AddBooksScreen(navController, passedData.arguments?.getString("staffId")!!)
        }
        composable("$ROUTE_BOOKS_HOME/{staffId}"){passedData->
            BooksHomeScreen(navController, passedData.arguments?.getString("staffId")!!)
        }
        composable("$ROUTE_EDIT_BOOKS/{bookId}"){passedData ->
            EditBooksScreen(navController, passedData.arguments?.getString("bookId")!!)  // need for edit
        }
        composable("$ROUTE_VIEW_BOOKS/{clientId}"){passedData ->
            ViewBooksScreen(navController, passedData.arguments?.getString("clientId")!!)  // need for edit
        }
        composable(ROUTE_VIEW_ALL_BOOKS){
            ViewAllBooksScreen(navController)  // need for edit
        }
        composable(ROUTE_VIEW_BOOKS_GUEST){
            ViewBooksGuest(navController)  // need for edit
        }
        composable("$ROUTE_BORROW_BOOKS/{clientId}/{bookId}"){ passedData ->
            BorrowBooksScreen(
                navController,
                passedData.arguments?.getString("clientId")!!,
                passedData.arguments?.getString("bookId")!!
            )
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
        composable("$ROUTE_VIEW_CLIENT_INFO/{clientId}"){passedData->
            ViewClientInfo(navController, passedData.arguments?.getString("clientId")!!)
        }
        composable("$ROUTE_EDIT_CLIENT_INFO/{clientId}"){ passedData->
            EditClientInfo(navController, passedData.arguments?.getString("clientId")!!)
        }
        composable("$ROUTE_VIEW_BORROWED_BOOKS/{clientId}"){passedData ->
            ViewBorrowedBooks(navController, passedData.arguments?.getString("clientId")!!)
        }
        composable(ROUTE_HOME){
            HomeScreen(navController)
        }
        composable("$ROUTE_RETURN_BOOKS/{clientId}/{bookId}"){passedData ->
            ReturnBooksScreen(navController,
                passedData.arguments?.getString("clientId")!!,
                passedData.arguments?.getString("bookId")!!
            )  //need for edit
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
        composable("$ROUTE_VIEW_STAFF_INFO/{staffId}"){passedData->
            ViewStaffInfo(navController, passedData.arguments?.getString("staffId")!!)
        }
        composable("$ROUTE_EDIT_STAFF_INFO/{staffId}"){ passedData->
            EditStaffInfo(navController, passedData.arguments?.getString("staffId")!!)
        }

    }
}
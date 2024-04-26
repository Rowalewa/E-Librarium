@file:Suppress("DEPRECATION")

package com.example.e_librarium.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.view.Gravity
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.e_librarium.models.Books
import com.example.e_librarium.navigation.ROUTE_ADD_BOOKS
import com.example.e_librarium.navigation.ROUTE_BOOKS_HOME
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class BooksViewModel (
    var navController: NavHostController,
    var context: Context
){
    private var authRepository: AuthViewModel = AuthViewModel(navController, context)
    private var progress: ProgressDialog

    init {
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_BOOKS_HOME)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Saving \uD83D\uDCBE")
        progress.setMessage("Please wait...")
    }
    fun saveBook(
        bookTitle: String,
        bookAuthor: String,
        bookYearOfPublication: String,
        bookPrice: String,
        bookISBNNumber: String,
        bookPublisher: String,
        bookPublicationDate: String,
        bookGenre: String,
        bookEdition: String,
        bookLanguage: String,
        bookNumberOfPages: String,
        bookAcquisitionMethod: String,
        bookCondition: String,
        bookShelfNumber: String,
        bookStatus: String,
        bookSynopsis: String,
        filePath: Uri
    ) {
        val id = System.currentTimeMillis().toString()
        val storageReference = FirebaseStorage.getInstance().getReference().child("Books/$id")
        storageReference.putFile(filePath).addOnCompleteListener{
            progress.show()
            if (
                bookTitle.isBlank() || bookAuthor.isBlank() || bookCondition.isBlank() || bookPrice.isBlank() ||
                bookISBNNumber.isBlank() || bookPublisher.isBlank() || bookPublicationDate.isBlank() ||
                bookEdition.isBlank()|| bookLanguage.isBlank()|| bookNumberOfPages.isBlank()|| bookAcquisitionMethod.isBlank()||
                bookYearOfPublication.isBlank()|| bookShelfNumber.isBlank()|| bookStatus.isBlank()|| bookSynopsis.isBlank()
            ) {
                progress.dismiss()
                Toast.makeText(context, "Fill all the fields please", Toast.LENGTH_LONG).show()
                // making a toast appear at the center of screen without using a variable
                navController.navigate(ROUTE_ADD_BOOKS)
            } else if (bookISBNNumber.length != 10 && bookISBNNumber.length != 13){
                progress.dismiss()
                Toast.makeText(context, "Invalid ISBN Number", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_ADD_BOOKS)
//
            } else if (it.isSuccessful){
                progress.dismiss()
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    val bookImageUrl = it.toString()
                    val houseData = Books(
                        bookTitle,
                        bookAuthor,
                        bookCondition,
                        bookPrice,
                        bookISBNNumber,
                        bookPublisher,
                        bookPublicationDate,
                        bookGenre,
                        bookEdition,
                        bookLanguage,
                        bookNumberOfPages,
                        bookAcquisitionMethod,
                        bookYearOfPublication,
                        bookShelfNumber,
                        bookStatus,
                        bookSynopsis,
                        bookImageUrl, id,
                    )
                    val dbRef = FirebaseDatabase.getInstance().getReference().child("Books/$id")
                    dbRef.setValue(houseData)
                    val toast = Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    navController.navigate(ROUTE_ADD_BOOKS)
                    // this is for making a toast centered on screen using variable
//                    navController.navigate(ROUTE_VIEW_UPLOAD_SCREEN)
                }
            }else{
                progress.dismiss()
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
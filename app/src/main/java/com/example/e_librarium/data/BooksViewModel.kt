@file:Suppress("DEPRECATION")

package com.example.e_librarium.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.example.e_librarium.models.Books
import com.example.e_librarium.models.BorrowingBook
import com.example.e_librarium.navigation.ROUTE_ADD_BOOKS
import com.example.e_librarium.navigation.ROUTE_BOOKS_HOME
import com.example.e_librarium.navigation.ROUTE_BORROW_BOOKS
import com.example.e_librarium.navigation.ROUTE_VIEW_BOOKS
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
        filePath: Uri,
        bookQuantity: Int // Add quantity as an integer parameter
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
                navController.navigate(ROUTE_ADD_BOOKS)
            } else if (bookISBNNumber.length != 10 && bookISBNNumber.length != 13){
                progress.dismiss()
                Toast.makeText(context, "Invalid ISBN Number", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_ADD_BOOKS)
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
                        bookImageUrl,
                        bookQuantity,
                        id// Pass quantity to the Books constructor
                    )
                    val dbRef = FirebaseDatabase.getInstance().getReference().child("Books/$id")
                    dbRef.setValue(houseData)
                    val toast = Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    navController.navigate(ROUTE_ADD_BOOKS)
                }
            } else {
                progress.dismiss()
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun viewBooks(
        book: MutableState<Books>,
        books: SnapshotStateList<Books>
    ): SnapshotStateList<Books> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Books")
//        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                progress.dismiss()
                books.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Books::class.java)
                    book.value = value!!
                    books.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return books
    }

    fun updateBook(
        bookId: String,
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
        bookQuantity: Int,
        filePath: Uri?
    ) {
        val storageReference = FirebaseStorage.getInstance().getReference().child("Books/$bookId")

        val updateData = Books(
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
            "",
            bookQuantity,// Placeholder for bookImageUrl
            bookId
        )

        // Update book details in Firebase Realtime Database
        if (filePath != null) {
        val dbRef = FirebaseDatabase.getInstance().getReference().child("Books/$bookId")
        dbRef.setValue(updateData).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // If an image file is provided, update the image in Firebase Storage
                    filePath.let { fileUri ->
                        storageReference.putFile(fileUri).addOnCompleteListener { storageTask ->
                            if (storageTask.isSuccessful) {
                                storageReference.downloadUrl.addOnSuccessListener { uri ->
                                    val imageUrl = uri.toString()
                                    updateData.bookImageUrl = imageUrl
                                    dbRef.setValue(updateData) // Update the book entry with the image URL
                                }
                            } else {
                                Toast.makeText(context, "Upload Failure", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    // Show success message
                    Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUTE_VIEW_BOOKS)
                } else {
                    // Handle database update error
                    Toast.makeText(context, "ERROR: ${task.exception?.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } else{
            val dbRef = FirebaseDatabase.getInstance().getReference().child("Books/$bookId")
            dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val book = snapshot.getValue(Books::class.java)
                    val existingImageUrl = book?.bookImageUrl ?: ""

                    val updateData = Books(
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
                        existingImageUrl,
                        bookQuantity,// Retain the existing image URL if filePath is null
                        bookId
                    )

                    dbRef.setValue(updateData).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Show success message
                            Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                            navController.navigate(ROUTE_VIEW_BOOKS)
                        } else {
                            // Handle database update error
                            Toast.makeText(context, "ERROR: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database error
                    Toast.makeText(context, "ERROR: ${error.message}", Toast.LENGTH_SHORT).show()
                }
            }
            )
            Toast.makeText(context, "Success with Image Retained", Toast.LENGTH_LONG).show()
        }
    }



    fun deleteBook(bookId: String) {
        val delRef = FirebaseDatabase.getInstance().getReference().child("Books/$bookId")
//        progress.show()
        delRef.removeValue().addOnCompleteListener {task ->
//            progress.dismiss()
            if (task.isSuccessful) {
                Log.d("DeleteBook", "Book deleted")
                Toast.makeText(context, "Book deleted", Toast.LENGTH_SHORT).show()
            } else {
                Log.e("DeleteBook", "Error deleting book", task.exception)
                Toast.makeText(context, "Error deleting book: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun borrowBook(
        bookId: String,
        clientId: String,
        borrowDate: String,
        returnDate: String
    ) {
        val bookRef = FirebaseDatabase.getInstance().getReference().child("Books").child(bookId)

        // Fetch the current status and quantity of the book
        bookRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val book = snapshot.getValue(Books::class.java)
                if (book != null) {
                    if (book.bookQuantity > 0) {
                        val borrowedBookData = BorrowingBook(clientId, bookId, borrowDate, returnDate)
                        val borrowedRef = FirebaseDatabase.getInstance().getReference().child("BorrowedBooks").push()

                        borrowedRef.setValue(borrowedBookData).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Update the book quantity and status
                                val newQuantity = book.bookQuantity - 1
                                bookRef.child("bookQuantity").setValue(newQuantity)
                                bookRef.child("bookStatus").setValue("Borrowed").addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Toast.makeText(context, "Book successfully borrowed", Toast.LENGTH_SHORT).show()
                                        navController.navigate(ROUTE_BOOKS_HOME)
                                    } else {
                                        Toast.makeText(context, "Failed to update book status", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            } else {
                                Toast.makeText(context, "Failed to borrow book", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Book is out of stock", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Failed to fetch book details", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to fetch book details", Toast.LENGTH_SHORT).show()
            }
        })
    }


    fun getBorrowedBooksForClient(clientId: String, callback: (List<BorrowingBook>) -> Unit) {
        val borrowedBooks = mutableListOf<BorrowingBook>()
        val dbRef = FirebaseDatabase.getInstance().getReference().child("BorrowedBooks")

        dbRef.orderByChild("clientId").equalTo(clientId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                borrowedBooks.clear()
                for (snap in snapshot.children) {
                    val borrowedBook = snap.getValue(BorrowingBook::class.java)
                    if (borrowedBook != null) {
                        borrowedBooks.add(borrowedBook)
                        Toast.makeText(context, "Borrowed book is null", Toast.LENGTH_LONG).show()
                    }
                }
                // Call the callback with the fetched data
                Toast.makeText(context, "Fetching books borrowed", Toast.LENGTH_LONG).show()
                callback(borrowedBooks)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
                Log.e("BorrowedBooks", "Error fetching borrowed books: ${error.message}")
                // Call the callback with an empty list or null to indicate failure
                callback(emptyList())
            }
        })
    }



}
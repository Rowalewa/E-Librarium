@file:Suppress("DEPRECATION")

package com.example.e_librarium.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.e_librarium.models.Books
import com.example.e_librarium.models.Clients
import com.example.e_librarium.models.Staff
import com.example.e_librarium.navigation.ROUTE_BOOKS_HOME
import com.example.e_librarium.navigation.ROUTE_BORROW_HOME
import com.example.e_librarium.navigation.ROUTE_CLIENT_HOME
import com.example.e_librarium.navigation.ROUTE_CLIENT_LOGIN
import com.example.e_librarium.navigation.ROUTE_CLIENT_REGISTER
import com.example.e_librarium.navigation.ROUTE_STAFF_HOME
import com.example.e_librarium.navigation.ROUTE_STAFF_LOGIN
import com.example.e_librarium.navigation.ROUTE_STAFF_REGISTER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class AuthViewModel (
    var navController: NavController,
    private var context: Context
){
    private var mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var progress: ProgressDialog = ProgressDialog(context)

    init {
        progress.setTitle("Loading...\uD83D\uDEE0\uFE0F ")
        progress.setMessage("Please wait for a moment")
    }

    fun staffsignup(
        fullName: String,
        gender: String,
        maritalStatus: String,
        phoneNumber: String,
        dateOfBirth: String,
        email: String,
        pass: String,
        confpass: String,
        staffProfilePictureUri: Uri
    ) {
        progress.show()
        if (fullName.isBlank() || gender.isBlank() || maritalStatus.isBlank() || phoneNumber.isBlank() || dateOfBirth.isBlank() || email.isBlank() || pass.isBlank() || confpass.isBlank()) {
            progress.dismiss()
            Toast.makeText(context, "Fill all the Fields Please \uD83D\uDE42", Toast.LENGTH_LONG).show()
            return
        }else if (pass != confpass) {
            progress.dismiss()
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
            navController.navigate(ROUTE_STAFF_REGISTER)
            return
        } else if (phoneNumber.length != 10){
            progress.dismiss()
            Toast.makeText(context, "Invalid Phone Number", Toast.LENGTH_LONG).show()
            return
        }else {
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val staffid = System.currentTimeMillis().toString()
                    val storageRef = FirebaseStorage.getInstance().reference
                    val profilePicRef = storageRef.child("staff_profile_pictures/${mAuth.currentUser!!.uid}")
                    profilePicRef.putFile(staffProfilePictureUri)
                        .addOnSuccessListener { _ ->
                            profilePicRef.downloadUrl.addOnSuccessListener { uri ->
                                // Once the image is uploaded, save the user data including the image URL
                                val staffProfilePictureUrl = uri.toString()
                                val staffdata = Staff(
                                    fullName,
                                    gender,
                                    maritalStatus,
                                    phoneNumber,
                                    dateOfBirth,
                                    email,
                                    pass,
                                    staffProfilePictureUrl, // Save the image URL in the user data
                                    staffid

                                )
                                val regRef = FirebaseDatabase.getInstance().getReference().child("Staff").child(mAuth.currentUser!!.uid)
                                regRef.setValue(staffdata).addOnCompleteListener { dataTask ->
                                    if (dataTask.isSuccessful) {
                                        progress.dismiss()
                                        Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG).show()
                                        navController.navigate(ROUTE_BOOKS_HOME)
                                    } else {
                                        progress.dismiss()
                                        Toast.makeText(context, "${dataTask.exception!!.message}", Toast.LENGTH_LONG).show()
                                        navController.navigate(ROUTE_STAFF_LOGIN)
                                    }
                                }
                            }
                        }
                        .addOnFailureListener { e ->
                            progress.dismiss()
                            Toast.makeText(context, "Failed to upload image: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                } else {
                    progress.dismiss()
                    val errorMessage = task.exception?.message ?: "Could not Register, Retry"
                    if (errorMessage.contains("email address is already in use")) {
                        Toast.makeText(context, "Email address is already registered", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                    }
                    navController.navigate(ROUTE_STAFF_REGISTER)
                }
            }
        }
    }

    fun clientsignup(
        fullName: String,
        gender: String,
        maritalStatus: String,
        phoneNumber: String,
        dateOfBirth: String,
        email: String,
        pass: String,
        confpass: String,
        clientProfilePictureUri: Uri
    ) {
        progress.show()
        if (fullName.isBlank() || gender.isBlank() || maritalStatus.isBlank() || phoneNumber.isBlank() || dateOfBirth.isBlank() || email.isBlank() || pass.isBlank() || confpass.isBlank()) {
            progress.dismiss()
            Toast.makeText(context, "Please fill in all the fields", Toast.LENGTH_LONG).show()
            navController.navigate(ROUTE_CLIENT_REGISTER)
            return
        }else if (pass != confpass) {
            progress.dismiss()
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
            navController.navigate(ROUTE_CLIENT_REGISTER)
            return
        } else if (phoneNumber.length != 10){
            Toast.makeText(context, "Invalid Phone Number", Toast.LENGTH_LONG).show()
        }else {
            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val clientid = System.currentTimeMillis().toString()
                    val storageRef = FirebaseStorage.getInstance().reference
                    val profilePicRef = storageRef.child("client_profile_pictures/${mAuth.currentUser!!.uid}")
                    profilePicRef.putFile(clientProfilePictureUri)
                        .addOnSuccessListener { _ ->
                            profilePicRef.downloadUrl.addOnSuccessListener { uri ->
                                val clientProfilePictureUrl = uri.toString()
                                val clientdata = Clients(
                                    fullName,
                                    gender,
                                    maritalStatus,
                                    phoneNumber,
                                    dateOfBirth,
                                    email,
                                    pass,
                                    clientProfilePictureUrl,
                                    clientid

                                )
                                val regRef =
                                    FirebaseDatabase.getInstance().getReference().child("Client")
                                        .child(mAuth.currentUser!!.uid)
                                regRef.setValue(clientdata).addOnCompleteListener { dataTask ->
                                    if (dataTask.isSuccessful) {
                                        progress.dismiss()
                                        Toast.makeText(
                                            context,
                                            "Registered Successfully",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        navController.navigate(ROUTE_BORROW_HOME)
                                    } else {
                                        progress.dismiss()
                                        Toast.makeText(
                                            context,
                                            "${dataTask.exception!!.message}",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        navController.navigate(ROUTE_CLIENT_LOGIN)
                                    }
                                }
                            }
                        }
                        .addOnFailureListener { e ->
                            progress.dismiss()
                            Toast.makeText(context, "Failed to upload image: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                } else {
                    progress.dismiss()
                    val errorMessage = task.exception?.message ?: "Could not Register, Retry"
                    if (errorMessage.contains("email address is already in use")) {
                        Toast.makeText(context, "Email address is already registered", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                    }
                    navController.navigate(ROUTE_CLIENT_REGISTER)
                }
            }
        }
    }


    fun clientlogin(
        email: String,
        pass: String
    ){
        progress.show()
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Successfully logged in", Toast.LENGTH_LONG).show()
                navController.navigate("$ROUTE_BORROW_HOME/{clientId}")
            } else {
                Toast.makeText(context,"${it.exception!!.message}", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_CLIENT_HOME)
            }
        }
    }

    fun stafflogin(
        email: String,
        pass: String
    ){
        progress.show()
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
            if (it.isSuccessful){
                progress.dismiss()
                Toast.makeText(context,"Successfully logged in", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_BOOKS_HOME)
            } else {
                progress.dismiss()
                Toast.makeText(context,"${it.exception!!.message}", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_STAFF_HOME)
            }
        }
    }

    fun stafflogout(){
        mAuth.signOut()
        navController.navigate(ROUTE_STAFF_HOME)
        Toast.makeText(context, "Successfully logged out", Toast.LENGTH_LONG).show()
    }

    fun clientlogout(){
        mAuth.signOut()
        navController.navigate(ROUTE_CLIENT_HOME)
        Toast.makeText(context, "Successfully logged out", Toast.LENGTH_LONG).show()
    }

    fun isloggedin(): Boolean{
        return mAuth.currentUser != null
    }

    fun viewClients(
        client: MutableState<Clients>,
        clients: SnapshotStateList<Clients>
    ): SnapshotStateList<Clients> {
        val ref = FirebaseDatabase.getInstance().getReference().child("Client")

//        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                progress.dismiss()
                clients.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Clients::class.java)
                    client.value = value!!
                    clients.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return clients
    }

    fun deleteClient(clientId: String) {
        val delRef = FirebaseDatabase.getInstance().getReference().child("Client/$clientId")
//        progress.show()
        delRef.removeValue().addOnCompleteListener {task ->
//            progress.dismiss()
            if (task.isSuccessful) {
                Log.d("Delete Client Account", "Client Account deleted")
                Toast.makeText(context, "Client Account deleted", Toast.LENGTH_SHORT).show()
            } else {
                Log.e("Delete Client Account", "Error deleting Client Account", task.exception)
                Toast.makeText(context, "Error deleting Client Account: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

}
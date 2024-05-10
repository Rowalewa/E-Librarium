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
                    val staffId = System.currentTimeMillis().toString()
                    val storageRef = FirebaseStorage.getInstance().reference
                    val profilePicRef = storageRef.child("staff_profile_pictures/${staffId}")
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
                                    staffId

                                )
                                val regRef = FirebaseDatabase.getInstance().getReference().child("Staff").child(staffId)
                                regRef.setValue(staffdata).addOnCompleteListener { dataTask ->
                                    if (dataTask.isSuccessful) {
                                        progress.dismiss()
                                        Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG).show()
                                        navController.navigate("$ROUTE_BOOKS_HOME/$staffId")
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
        clientProfilePictureUri: Uri,
        clientStatus: String
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
                    val clientId = System.currentTimeMillis().toString()
                    val storageRef = FirebaseStorage.getInstance().reference
                    val profilePicRef = storageRef.child("client_profile_pictures/${clientId}")
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
                                    clientStatus,
                                    clientId


                                )
                                val regRef = FirebaseDatabase.getInstance().getReference().child("Client").child(clientId)
                                regRef.setValue(clientdata).addOnCompleteListener { dataTask ->
                                    if (dataTask.isSuccessful) {
                                        progress.dismiss()
                                        Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG).show()
                                        navController.navigate("$ROUTE_BORROW_HOME/$clientId")
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

    private fun getClientIdByEmail(email: String, callback: (String?) -> Unit) {
        val ref = FirebaseDatabase.getInstance().getReference().child("Client")
        ref.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snap in snapshot.children) {
                        val clientId = snap.key // Assuming the client ID is the key of the client node
                        callback(clientId)
                        return
                    }
                }
                callback(null) // Client ID not found
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Client", "Error fetching client ID: ${error.message}")
                callback(null) // Handle the error by returning null
            }
        })
    }


    fun clientlogin(
        email: String,
        pass: String,
    ){
        progress.show()
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
            progress.dismiss()
            getClientIdByEmail(email) { clientId ->
                if (clientId != null) {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Successfully logged in", Toast.LENGTH_LONG).show()
                        navController.navigate("$ROUTE_BORROW_HOME/$clientId")
                    } else {
                        Toast.makeText(context, "${it.exception!!.message}", Toast.LENGTH_LONG)
                            .show()
                        navController.navigate(ROUTE_CLIENT_HOME)
                    }
                } else {
                    Toast.makeText(context, "Failed to fetch client id", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun updateClient(
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
        filePath: Uri?
    ) {
        val storageReference = FirebaseStorage.getInstance().getReference().child("Client/$clientId")

        val updateData = Clients(
            fullName,
            gender,
            maritalStatus,
            phoneNumber,
            dateOfBirth,
            email,
            pass,
            "",
            clientStatus,
            clientId
        )

        // Update book details in Firebase Realtime Database
        if (filePath != null) {
            val user = FirebaseAuth.getInstance().currentUser
            user?.updatePassword(pass)?.addOnCompleteListener { passwordUpdateTask ->
                if (passwordUpdateTask.isSuccessful) {
                    if (pass == confpass) {
                        val dbRef = FirebaseDatabase.getInstance().getReference().child("Client/$clientId")
                        dbRef.setValue(updateData).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // If an image file is provided, update the image in Firebase Storage
                                filePath.let { fileUri ->
                                    storageReference.putFile(fileUri)
                                        .addOnCompleteListener { storageTask ->
                                            if (storageTask.isSuccessful) {
                                                storageReference.downloadUrl.addOnSuccessListener { uri ->
                                                    val imageUrl = uri.toString()
                                                    updateData.clientProfilePictureUrl = imageUrl
                                                    dbRef.setValue(updateData) // Update the book entry with the image URL
                                                }
                                            } else {
                                                Toast.makeText(context, "Upload Failure", Toast.LENGTH_LONG).show()
                                            }
                                        }
                                }

                                // Show success message
                                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            } else {
                                // Handle database update error
                                Toast.makeText(context, "ERROR: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Passwords Update task failure", Toast.LENGTH_LONG)
                        .show()
                }
            }
        } else {
            val user = FirebaseAuth.getInstance().currentUser
            user?.updatePassword(pass)?.addOnCompleteListener { passwordUpdateTask ->
                if (passwordUpdateTask.isSuccessful) {
                    if (pass == confpass) {
                        val dbRef = FirebaseDatabase.getInstance().getReference().child("Client/$clientId")
                        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val client = snapshot.getValue(Clients::class.java)
                                val existingImageUrl = client?.clientProfilePictureUrl ?: ""

                                val updateData = Clients(
                                    fullName,
                                    gender,
                                    maritalStatus,
                                    phoneNumber,
                                    dateOfBirth,
                                    email,
                                    pass,
                                    clientStatus,
                                    existingImageUrl,
                                    clientId
                                )

                                dbRef.setValue(updateData).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        // Show success message
                                        Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                                        navController.popBackStack()
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
                        })
                        Toast.makeText(context, "Success with Image Retained", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Password Update Task Failure", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    private fun getStaffIdByEmail(email: String, callback: (String?) -> Unit) {
        val ref = FirebaseDatabase.getInstance().getReference().child("Staff")
        ref.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snap in snapshot.children) {
                        val staffId = snap.key // Assuming the client ID is the key of the client node
                        callback(staffId)
                        return
                    }
                }
                callback(null) // Client ID not found
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Staff", "Error fetching staff ID: ${error.message}")
                callback(null) // Handle the error by returning null
            }
        })
    }

    fun stafflogin(
        email: String,
        pass: String
    ){
        progress.show()
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            progress.dismiss()
            getStaffIdByEmail(email) { staffId ->
                if (staffId != null) {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Successfully logged in", Toast.LENGTH_LONG).show()
                        navController.navigate("$ROUTE_BOOKS_HOME/$staffId")
                    } else {
                        Toast.makeText(context, "${it.exception!!.message}", Toast.LENGTH_LONG)
                            .show()
                        navController.navigate(ROUTE_STAFF_HOME)
                    }
                } else {
                    Toast.makeText(context, "Staff is null", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun updateStaff(
        fullName: String,
        gender: String,
        maritalStatus: String,
        phoneNumber: String,
        dateOfBirth: String,
        email: String,
        pass: String,
        confpass: String,
        staffId: String,
        filePath: Uri?
    ) {
        val storageReference = FirebaseStorage.getInstance().getReference().child("Staff/$staffId")

        val updateData = Staff(
            fullName,
            gender,
            maritalStatus,
            phoneNumber,
            dateOfBirth,
            email,
            pass,
            "",
            staffId
        )

        // Update book details in Firebase Realtime Database
        if (filePath != null) {
            val user = FirebaseAuth.getInstance().currentUser
            user?.updatePassword(pass)?.addOnCompleteListener { passwordUpdateTask ->
                if (passwordUpdateTask.isSuccessful) {
                    if (pass == confpass) {
                        val dbRef = FirebaseDatabase.getInstance().getReference().child("Staff/$staffId")
                        dbRef.setValue(updateData).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // If an image file is provided, update the image in Firebase Storage
                                filePath.let { fileUri ->
                                    storageReference.putFile(fileUri)
                                        .addOnCompleteListener { storageTask ->
                                            if (storageTask.isSuccessful) {
                                                storageReference.downloadUrl.addOnSuccessListener { uri ->
                                                    val imageUrl = uri.toString()
                                                    updateData.staffProfilePictureUrl = imageUrl
                                                    dbRef.setValue(updateData) // Update the book entry with the image URL
                                                }
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Upload Failure",
                                                    Toast.LENGTH_LONG
                                                )
                                                    .show()
                                            }
                                        }
                                }

                                // Show success message
                                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT)
                                    .show()
                                navController.popBackStack()
                            } else {
                                // Handle database update error
                                Toast.makeText(
                                    context,
                                    "ERROR: ${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Passwords Update task failure", Toast.LENGTH_LONG)
                        .show()
                }
            }
        } else {
            val user = FirebaseAuth.getInstance().currentUser
            user?.updatePassword(pass)?.addOnCompleteListener { passwordUpdateTask ->
                if (passwordUpdateTask.isSuccessful) {
                    if (pass == confpass) {
                        val dbRef = FirebaseDatabase.getInstance().getReference().child("Staff/$staffId")
                        dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val staff = snapshot.getValue(Staff::class.java)
                                val existingImageUrl = staff?.staffProfilePictureUrl ?: ""

                                val updateData = Staff(
                                    fullName,
                                    gender,
                                    maritalStatus,
                                    phoneNumber,
                                    dateOfBirth,
                                    email,
                                    pass,
                                    existingImageUrl,
                                    staffId
                                )

                                dbRef.setValue(updateData).addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        // Show success message
                                        Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                                        navController.popBackStack()
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
                        })
                        Toast.makeText(context, "Success with Image Retained", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(context, "Password Update Task Failure", Toast.LENGTH_LONG).show()
                }
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
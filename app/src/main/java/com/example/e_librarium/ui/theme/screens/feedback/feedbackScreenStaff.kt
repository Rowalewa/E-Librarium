package com.example.e_librarium.ui.theme.screens.feedback

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.e_librarium.data.FeedbackViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun FeedbackScreenStaff(navController: NavHostController, staffId: String) {
    val context = LocalContext.current
    var feedbackName by remember {
        mutableStateOf(TextFieldValue())
    }
    var feedbackEmailAddress by remember {
        mutableStateOf(TextFieldValue())
    }
    var feedbackText by remember {
        mutableStateOf(TextFieldValue())
    }
    val staffFullNameRef = FirebaseDatabase.getInstance().getReference("Staff").child(staffId).child("fullName")
    staffFullNameRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val staffFullName = snapshot.getValue(String::class.java)
            if (staffFullName != null) {
                feedbackName = TextFieldValue(staffFullName)
            }
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle database error
            Toast.makeText(context, "Database Error: ${error.message}", Toast.LENGTH_SHORT).show()
        }
    })
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "We'd love to hear your feedback!",
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(
            value = feedbackName,
            onValueChange = { feedbackName = it },
            label = { Text("Enter your name here *") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = feedbackEmailAddress,
            onValueChange = { feedbackEmailAddress = it },
            label = { Text("Enter your email here (Optional)") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = feedbackText,
            onValueChange = { feedbackText = it },
            label = { Text("Enter your feedback here *") },
            modifier = Modifier.fillMaxWidth()
                .height(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val feedbackRepository = FeedbackViewModel(navController, context)
                feedbackRepository.saveFeedbackToFirebaseStaff(
                    feedbackName.text.trim(),
                    feedbackEmailAddress.text.trim(),
                    feedbackText.text.trim()
                )
//                feedbackText = TextFieldValue()
//                feedbackEmailAddress = TextFieldValue()
//                feedbackName = TextFieldValue()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit")
        }
    }
}

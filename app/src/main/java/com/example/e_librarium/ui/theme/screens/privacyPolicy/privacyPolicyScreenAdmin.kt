package com.example.e_librarium.ui.theme.screens.privacyPolicy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.e_librarium.ui.theme.screens.admin.AdminAppTopBar
import com.example.e_librarium.ui.theme.screens.admin.AdminBottomAppBar
import com.example.e_librarium.ui.theme.screens.borrowing.ClientAppTopBar
import com.example.e_librarium.ui.theme.screens.borrowing.ClientBottomAppBar

@Composable
fun PrivacyPolicyScreenAdmin(navController: NavController, adminId: String){
    Box{
        Column {
            Box (
                modifier = Modifier.fillMaxWidth()
            ){
                AdminAppTopBar(navController, adminId)
            }
            Column (
                modifier = Modifier.verticalScroll(state = rememberScrollState(), enabled = true, reverseScrolling = false)
            ){
                Text(text = "Privacy Policy")
                Text(
                    text = "Information We Collect:\n" +
                            "\n" +
                            "We collect personal information, such as name and email address, when you register for an account. We also collect usage data, such as pages visited and interactions with the app, to improve our services.\n"
                )
                Text(
                    text = "How We Use Your Information: \n" +
                            "\n" +
                            "We use your personal information to provide the services you request and to personalize your experience. We may also use your information for marketing purposes with your consent."
                )
                Text(
                    text = "Information Sharing: \n" +
                            "\n" +
                            "We do not sell or share your personal information with third parties for their marketing purposes. We may share your information with service providers who help us operate the app."
                )
                Text(
                    text = "Data Security: \n" +
                            "\n" +
                            "We take reasonable measures to protect your information from unauthorized access, disclosure, or alteration. However, no method of transmission over the internet or electronic storage is 100% secure.\n"
                )
                Text(
                    text = "Your Choices: \n" +
                            "\n" +
                            "You can access and update your personal information through your account settings. You can also opt-out of marketing communications at any time."
                )
                Text(
                    text = "Changes to This Policy: \n" +
                            "\n" +
                            "We may update this Privacy Policy from time to time. We will notify you of any changes by posting the new Privacy Policy on this page."
                )
                Spacer(modifier = Modifier.height(70.dp))
            }
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            AdminBottomAppBar(navController, adminId)
        }
    }
}
package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        auth = FirebaseAuth.getInstance() // Initialize Firebase Auth

        return ComposeView(requireContext()).apply {
            setContent {
                ForgotPasswordScreen(
                    onRecoverPassword = { email ->
                        sendPasswordResetEmail(email)
                    },
                    onGoBackToLogin = {
                        findNavController().navigate(R.id.login)
                    }
                )
            }
        }
    }

    /** Function to validate email format */
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun sendPasswordResetEmail(email: String) {
        if (!isValidEmail(email)) {
            Toast.makeText(requireContext(), "Invalid Email Format", Toast.LENGTH_SHORT).show()
            return
        }

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Reset link sent to your email", Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.login)
                } else {
                    Toast.makeText(context, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }
}

@Composable
fun ForgotPasswordScreen(onRecoverPassword: (String) -> Unit, onGoBackToLogin: () -> Unit) {
    var email by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.forgot_password),
            contentDescription = "Forgot Password Image",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onRecoverPassword(email.text) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recover Password")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onGoBackToLogin) {
            Text("Go Back to Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(onRecoverPassword = {}, onGoBackToLogin = {})
}

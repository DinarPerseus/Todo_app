package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth

class Login : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.login, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.composeView)

        auth = Firebase.auth // Initialize Firebase Auth

        composeView.setContent {
            LoginPage(
                onLoginClick = { email, password ->
                    loginUser(email, password)
                },
                onSignUpClick = {
                    findNavController().navigate(R.id.registration)
                },
                onForgotPasswordClick = {
                   // findNavController().navigate(R.id.forgotPassword)
                }
            )
        }

        return view


    }

    /** Function to validate email format */
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    /** Function to handle Firebase login with validation */
    private fun loginUser(email: String, password: String) {
        if (!isValidEmail(email)) {
            Toast.makeText(requireContext(), "Invalid Email Format", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.length < 6) {
            Toast.makeText(requireContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish() // Close login page
                } else {
                    Toast.makeText(requireContext(), "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }


    @Composable
    fun LoginPage(
        onLoginClick: (String, String) -> Unit,
        onSignUpClick: () -> Unit,
        onForgotPasswordClick: () -> Unit
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf("") }
        val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Default user image
            Image(
                painter = painterResource(id = R.drawable.login), // Replace with your image
                contentDescription = "User Image",
                modifier = Modifier
                    .size(150.dp) // Size of the image
                    //.clip(CircleShape) // Make it circular
                    //.border(2.dp, Color.Gray, CircleShape) // Optional: Add border around image
            )
            Image(
                painter = painterResource(id = R.drawable.login_text), // Replace with your image
                contentDescription = "User Image",
                modifier = Modifier
                    .size(150.dp) // Size of the image
                //.clip(CircleShape) // Make it circular
                //.border(2.dp, Color.Gray, CircleShape) // Optional: Add border around image
            )
//            Spacer(modifier = Modifier.height(32.dp))
//            Text(
//                text = "Login",
//                color = Color.Gray,
//                fontSize = 32.sp,
//                fontWeight = FontWeight.Bold,
//                textAlign = TextAlign.Center
//            )

            //Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = errorMessage.contains("Email")
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = null)
                    }
                },
                isError = errorMessage.contains("Password")
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Forgot Password?",
                modifier = Modifier
                    .clickable { onForgotPasswordClick() }
                    .align(Alignment.End),
                color = Color.Blue
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (email.isEmpty() || password.isEmpty()) {
                        errorMessage = "Email and Password cannot be empty"
                    } else {
                        errorMessage = ""
                        onLoginClick(email, password)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Don't have an account?")
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "Sign Up",
                    color = Color.Blue,
                    modifier = Modifier.clickable { onSignUpClick() }
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LoginPagePreview() {
        LoginPage(
            onLoginClick = { _, _ -> },
            onSignUpClick = {},
            onForgotPasswordClick = {}
        )
    }
}

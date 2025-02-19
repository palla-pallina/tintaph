package com.example.tintaph

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val emailEditText = findViewById<EditText>(R.id.emailTXT)
        val passwordEditText = findViewById<EditText>(R.id.passwordTXT)
        val signUpButton = findViewById<Button>(R.id.signUpBTN)
        val haveAcc = findViewById<TextView>(R.id.textView)

        // Redirect to Log In
        haveAcc.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }

        // Register User
        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.length >= 6) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Registration successful
                            Toast.makeText(this, "Sign-up successful!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, HomeFeed::class.java))
                            finish() // Prevents user from going back to SignUpActivity
                        } else {
                            // Failed sign-up
                            Toast.makeText(this, "Sign-up failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Enter a valid email and at least 6-character password!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

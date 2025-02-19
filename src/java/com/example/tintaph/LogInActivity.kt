package com.example.tintaph

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in)

        auth = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.usernameTXT)
        passwordEditText = findViewById(R.id.passwordTXT)

        // BUTTON: Redirect to Sign Up
        val signUpButton = findViewById<Button>(R.id.signupredBTN)
        signUpButton.setOnClickListener {
            startActivity(Intent(this@LogInActivity, SignUpActivity::class.java))
        }

        // BUTTON: Login and Redirect to Home Feed if successful
        val logInButton = findViewById<Button>(R.id.loginBTN)
        logInButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@LogInActivity, HomeFeed::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter email and password.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

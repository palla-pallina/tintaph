package com.example.tintaph

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val GetStartedButton = findViewById<Button>(R.id.get_started)
        GetStartedButton.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)


        }
    }
}

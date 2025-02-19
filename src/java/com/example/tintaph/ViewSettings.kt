package com.example.tintaph

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ViewSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_settings)

        // Go Back to Home Feed
        val backButton = findViewById<ImageView>(R.id.backBTN)
        backButton.setOnClickListener {
            startActivity(Intent(this@ViewSettings, HomeFeed::class.java))
        }

        // ....log out
        val logOut = findViewById<LinearLayout>(R.id.logout)
        logOut.setOnClickListener{
            startActivity(Intent(this@ViewSettings, MainActivity::class.java))
        }
    }
}
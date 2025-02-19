package com.example.tintaph

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_profile)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Image Data
        val imageList = listOf(
            R.drawable.placeholder4, R.drawable.placeholder4, R.drawable.placeholder4
        )

        // Set up RecyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 Columns
        recyclerView.adapter = ImageAdapter(imageList)

        ///////////////////////////////////////////////////////////////// TOP NAVIGATION
        // Go Back to Home Feed
        val backButton = findViewById<ImageView>(R.id.backBTN)
        backButton.setOnClickListener {
            startActivity(Intent(this@UserProfile, HomeFeed::class.java))
        }

        ///////////////////////////////////////////////////////////////// BOTTOM NAV
        // Bottom Navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this@UserProfile, HomeFeed::class.java))
                    true
                }
                R.id.calendar -> {
                    startActivity(Intent(this@UserProfile, DisplayEvents::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
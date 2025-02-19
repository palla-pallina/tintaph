package com.example.tintaph

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

private lateinit var drawerLayout: DrawerLayout
private lateinit var navigationView: NavigationView
private lateinit var navButton: ImageButton

class UploadArt : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload_art)

        // Upload File Button Click Listener
        val uplButton = findViewById<Button?>(R.id.publishBTN)
        uplButton.setOnClickListener {
            startActivity(Intent(this@UploadArt, HomeFeed::class.java))
        }

        ///////////////////////////////////////////////////////////////// TOP NAVIGATION
        // Redirect to User Profile
        val userIconClick = findViewById<ImageView>(R.id.navUser)
        userIconClick.setOnClickListener {
            startActivity(Intent(this@UploadArt, UserProfile::class.java))
        }

        // Go Back to Home Feed
        val backButton = findViewById<ImageView>(R.id.backBTN)
        backButton.setOnClickListener {
            startActivity(Intent(this@UploadArt, HomeFeed::class.java))
        }

        ///////////////////////////////////////////////////////////////// BOTTOM NAV
        // Bottom Navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this@UploadArt, HomeFeed::class.java))
                    true
                }
                R.id.calendar -> {
                    startActivity(Intent(this@UploadArt, DisplayEvents::class.java))
                    true
                }
                else -> false
            }
        }


        // Go Back to Home Feed
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this@UploadArt, HomeFeed::class.java))
                    true
                }
                R.id.calendar -> {
                    startActivity(Intent(this@UploadArt, DisplayEvents::class.java))
                    true
                }
                else -> false
            }
        }

        // Navigation Drawer setup
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigationView)
        navButton = findViewById(R.id.navButton)

        navButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END) // Open from right
        }

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_notifications -> {
                    Toast.makeText(this, "Notifications clicked", Toast.LENGTH_SHORT).show()
                }

                R.id.nav_settings -> {
                    startActivity(Intent(this@UploadArt   , ViewSettings::class.java))
                }

                R.id.nav_logout -> {
                    startActivity(Intent(this@UploadArt, MainActivity::class.java))
                }
            }
            drawerLayout.closeDrawer(GravityCompat.END)
            true
        }

    }
}
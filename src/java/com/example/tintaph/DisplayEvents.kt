package com.example.tintaph

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

private lateinit var drawerLayout: DrawerLayout
private lateinit var navigationView: NavigationView
private lateinit var navButton: ImageButton

class DisplayEvents : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_events)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewEvents)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Go Back to Home Feed
        val backButton = findViewById<ImageView>(R.id.backBTN)
        backButton.setOnClickListener {
            startActivity(Intent(this@DisplayEvents, HomeFeed::class.java))
        }

        // Navigate to User Profile (top nav)
        val userIconClick = findViewById<ImageView>(R.id.navUser)
        userIconClick.setOnClickListener {
            startActivity(Intent(this@DisplayEvents, UserProfile::class.java))
        }

        // Sample local events
        val events = listOf(
            Event(R.drawable.e_pasinaya, "CCP Pasinaya Festival 2025", "Circuit Makati, February 1-2", "Experience remarkable performances from talented Filipino artists and explore the richness of Philippine art with friends and fellow art lovers.", false),
            Event(R.drawable.e_animafes, "1st Animation Festival 2025", "Lorma Colleges Campus for Learning and Innovation, February 6-7", "With the theme “Animating Futures, Bridging Cultures,” the festival aimed to highlight the power of animation as a tool for cultural exchange, innovation, and collaboration.", false),
            Event(R.drawable.e_komiket, "Komiket The Filipino Komiks Art Market", "SM Megamall Megatrade Hall 2, February 8-9", "", false),
            Event(R.drawable.e_youngartists, "Greenhills Young Artist Festival 2025", "Greenhills Mall, GF Atrium", "Art Merchandise + Live Portrait Session\n" +
                    "Workshop on Storytelling through Illustration\n" +
                    "March\t\t\t\t\t", false)
        )

        val adapter = EventAdapter(events) { event ->
            event.isBookmarked = !event.isBookmarked
            recyclerView.adapter?.notifyItemChanged(events.indexOf(event))
        }

        recyclerView.adapter = adapter

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
                    startActivity(Intent(this@DisplayEvents, ViewSettings::class.java))
                }

                R.id.nav_logout -> {
                    startActivity(Intent(this@DisplayEvents, MainActivity::class.java))
                }
            }
            drawerLayout.closeDrawer(GravityCompat.END)
            true
        }
    }
}

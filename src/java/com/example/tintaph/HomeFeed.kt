package com.example.tintaph

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

private lateinit var drawerLayout: DrawerLayout
private lateinit var navigationView: NavigationView
private lateinit var navButton: ImageButton

class HomeFeed : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_feed)

        // Upload Button Click Listener
        val uplButton = findViewById<ImageView>(R.id.uploadButton)
        uplButton.setOnClickListener {
            startActivity(Intent(this@HomeFeed, UploadArt::class.java))
        }

        // Navigate to User Profile
        val navButtonIcon = findViewById<ImageView>(R.id.navButton)
        navButtonIcon.setOnClickListener {
            startActivity(Intent(this@HomeFeed, UserProfile::class.java))
        }

        // Navigate to User Profile (top nav)
        val userIconClick = findViewById<ImageView>(R.id.navUser)
        userIconClick.setOnClickListener {
            startActivity(Intent(this@HomeFeed, UserProfile::class.java))
        }

        // Bottom Navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this@HomeFeed, HomeFeed::class.java))
                    true
                }
                R.id.calendar -> {
                    startActivity(Intent(this@HomeFeed, DisplayEvents::class.java))
                    true
                }
                else -> false
            }
        }

        // Scroll Gallery setup
        val imageList = listOf(
            R.drawable.ahh_renzieq, R.drawable.biyaya_jarrendahan, R.drawable.filipina_beauty,
            R.drawable.frens, R.drawable.good_luck, R.drawable.heehee_nhoacamil,
            R.drawable.lit_rug_sobsanix, R.drawable.mga_doggo, R.drawable.mutya,
            R.drawable.nikynik, R.drawable.photo_studdyyy, R.drawable.prayer,
            R.drawable.starboi_insp, R.drawable.redrawag, R.drawable.tux,
            R.drawable.thecruelprince, R.drawable.sobranix, R.drawable.sneakpeak,
            R.drawable.seeinggreen, R.drawable.redrawag, R.drawable.missnapo,
            R.drawable.lit_rug_sobsanix, R.drawable.lightof, R.drawable.donchild,
            R.drawable.cloudstudy1,


        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 Columns
        recyclerView.adapter = ImageAdapter(imageList)

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
                    startActivity(Intent(this@HomeFeed, ViewSettings::class.java))
                }

                R.id.nav_logout -> {
                    startActivity(Intent(this@HomeFeed, MainActivity::class.java))
                }
            }
            drawerLayout.closeDrawer(GravityCompat.END)
            true
        }
    }
}

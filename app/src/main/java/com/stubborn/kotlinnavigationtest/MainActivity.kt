package com.stubborn.kotlinnavigationtest

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var mAppBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = AppBarConfiguration.Builder(
            R.id.nav_home, R.id.nav_about, R.id.nav_contact, R.id.nav_info
        )
            .setDrawerLayout(drawer)
            .build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration!!)
        NavigationUI.setupWithNavController(navigationView, navController)


        navigationView.bringToFront()
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomeFragment()).commit()
                    Toast.makeText(this@MainActivity, "Home is selected", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_about -> {

                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, AboutFragment()).commit()
                    Toast.makeText(this@MainActivity, "About is selected", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_contact -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ContactFragment()).commit()
                    Toast.makeText(this@MainActivity, "Contact is selected", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_info -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, InfoFragment()).commit()
                    Toast.makeText(this@MainActivity, "info is selected", Toast.LENGTH_SHORT).show()
                }
            }
            drawer.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return (mAppBarConfiguration?.let { NavigationUI.navigateUp(navController, it) }!!
                || super.onSupportNavigateUp())
    }
}

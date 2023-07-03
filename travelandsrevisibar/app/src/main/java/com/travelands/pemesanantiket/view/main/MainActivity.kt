package com.travelands.pemesanantiket.view.main

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.travelands.pemesanantiket.R.menu.navigation_menu
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.travelands.pemesanantiket.R
import com.travelands.pemesanantiket.dashboardiklan.IklanActivity
import com.travelands.pemesanantiket.login.LoginActivity
import com.travelands.pemesanantiket.view.history.HistoryActivity
import com.travelands.pemesanantiket.view.input.DataKapalActivity
import com.travelands.pemesanantiket.view.input.DataKeretaActivity
import com.travelands.pemesanantiket.view.input.DataPesawatActivity
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusBar(barnav)
        setToolbar()

        iklan.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, IklanActivity::class.java)
            startActivity(intent)
        }

        imageProfile.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
        }

        cvPesawat.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, DataPesawatActivity::class.java)
            startActivity(intent)
        }

        cvKapal.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, DataKapalActivity::class.java)
            startActivity(intent)
        }

        cvKereta.setOnClickListener { v: View? ->
            val intent = Intent(this@MainActivity, DataKeretaActivity::class.java)
            startActivity(intent)
        }


        val navController = findNavController(R.id.fragment_container)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        NavigationUI.setupWithNavController(navView, navController)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            barnav,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)

        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun setStatusBar(barnav: Toolbar) {
        if (Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }
}





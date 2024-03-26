package com.androidvynils.app.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.androidvynils.app.R
import com.androidvynils.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val ACCESS_NETWORK_STATE_PERMISSION_REQUEST_CODE = 1002
    private val INTERNET_PERMISSION_REQUEST_CODE = 1003
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            // Permission is already granted
            // Perform your operation that requires this permission
        } else {
            // Permission is not yet granted
            // Request the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.INTERNET), INTERNET_PERMISSION_REQUEST_CODE)
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {
            // Permission is already granted
            // Perform your operation that requires this permission
        } else {
            // Permission is not yet granted
            // Request the permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_NETWORK_STATE), ACCESS_NETWORK_STATE_PERMISSION_REQUEST_CODE)
        }
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the navigation host fragment from this Activity
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController
        // Make sure actions in the ActionBar get propagated to the NavController
        Log.d("act", navController.toString())
        setSupportActionBar(findViewById(R.id.my_toolbar))
        setupActionBarWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
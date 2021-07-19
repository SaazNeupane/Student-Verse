package com.example.studentverse.activity.ui

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.studentverse.R
import com.example.studentverse.activity.fragments.HomeFragment
import com.example.studentverse.activity.fragments.OthersFragment
import com.example.studentverse.activity.fragments.SearchFragment
import com.example.studentverse.activity.fragments.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class DashboardActivity : AppCompatActivity(){
    private lateinit var bottomnavigation: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        bottomnavigation = findViewById(R.id.bottomnavigation)
        menuitem()


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, HomeFragment())
            addToBackStack(null)
            commit()
        }


    }


    private fun menuitem(){
        bottomnavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.container, HomeFragment())
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                R.id.search -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.container, SearchFragment())
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                R.id.user -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.container, UserFragment())
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                R.id.others -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.container, OthersFragment())
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                else -> false
            }
        }
    }

}
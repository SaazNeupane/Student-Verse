package com.example.studentverse.activity.ui

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.studentverse.R
import com.example.studentverse.activity.fragment.StudyMaterialFragment
import com.example.studentverse.activity.fragment.HomeFragment
import com.example.studentverse.activity.fragment.SearchFragment
import com.example.studentverse.activity.fragment.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

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
                R.id.studymaterials -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.container, StudyMaterialFragment())
                        addToBackStack(null)
                        commit()
                    }
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are you Sure?")
        //set message for alert dialog
        builder.setMessage("You wanna quit the app?")
        builder.setIcon(R.drawable.alert)

        //performing positive action
        builder.setPositiveButton("Yes"){ _, _ ->
            finish()
        }
        //performing negative action
        builder.setNegativeButton("No"){ _, _ ->
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

}
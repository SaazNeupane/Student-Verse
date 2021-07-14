package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.studentverse.R
import com.example.studentverse.activity.api.APIRequest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.api.UserAPI
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.repository.UserRepository
import com.example.studentverse.activity.response.CurrentUserRespone
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ProfileActivity : AppCompatActivity() {
    private lateinit var updateprofile: TextView
    private lateinit var profilefname: TextView
    private lateinit var profilelname: TextView
    private lateinit var profileusername: TextView
    private lateinit var profileemail: TextView
    private lateinit var profileadd: TextView
    private lateinit var profilemobile: TextView

    private var userDetails: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)
        updateprofile = findViewById(R.id.updateprofile)
        profilefname = findViewById(R.id.profilefname)
        profilelname = findViewById(R.id.profilelname)
        profileusername = findViewById(R.id.profileusername)
        profileemail = findViewById(R.id.profileemail)
        profileadd = findViewById(R.id.profileadd)
        profilemobile = findViewById(R.id.profilemobile)

        loaduser()
    }

    private fun loaduser() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.profile()
                println("This is success of profile "+ response.success)
                if (response.success == true) {
                    userDetails = response.data!!
                    println("userdata"+userDetails)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ProfileActivity, "${userDetails}}", Toast.LENGTH_LONG).show()
                        profilefname.text = "Welcome, ${userDetails!!.fname}"
                        profilelname.text = "lname: ${userDetails!!.lname}"
                        profileusername.text = "username: ${userDetails!!.username}"
                        profileemail.text = "email: ${userDetails!!.email}"
                        profileadd.text = "address: ${userDetails!!.address}"
                        profilemobile.text = "mobile: ${userDetails!!.mobile}"
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@ProfileActivity,
                        "Profile Error : $ex", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
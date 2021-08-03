package com.example.studentverse.activity.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.studentverse.R
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.notification.NotificationChannels
import com.example.studentverse.activity.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var etfname: EditText
    private lateinit var etlname: EditText
    private lateinit var etmobile: EditText
    private lateinit var etaddress: EditText
    private lateinit var btnupdate: Button

    private var userDetails: User? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        etfname=findViewById(R.id.etufname)
        etlname=findViewById(R.id.etulname)
        etmobile=findViewById(R.id.etumobile)
        etaddress=findViewById(R.id.etuaddress)
        btnupdate=findViewById(R.id.btnupdatedetails)

        loaduser()

        //button
        btnupdate.setOnClickListener {
            update()
        }
    }

    private fun loaduser() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.profile()

                if (response.success == true) {
                    userDetails = response.data!!
                    withContext(Dispatchers.Main) {
                        etfname.setText( "${userDetails!!.fname}")
                        etfname.setText( "${userDetails!!.lname}")
                        etaddress.setText( "${userDetails!!.address}")
                        etmobile.setText( "${userDetails!!.mobile}")
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@UpdateProfileActivity,
                        "Error : $ex", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun update(){
        val fname=etfname.text.toString()
        val lname=etlname.text.toString()
        val address=etaddress.text.toString()
        val mobile=etmobile.text.toString()

        val details = User(fname=fname,lname = lname,address = address,mobile = mobile)

        CoroutineScope(Dispatchers.IO).launch{
            try{
                val userRepository = UserRepository()
                val response = userRepository.update(details)
                if (response.success == true){
                    withContext(Dispatchers.Main){
                        showNotification("${response.message}")
                        startActivity(
                            Intent(
                                this@UpdateProfileActivity,
                                DashboardActivity::class.java
                            )
                        )
                    }
                }
            }
            catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@UpdateProfileActivity,
                        ex.toString(),
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showNotification(message: String) {

        val notificationManager = NotificationManagerCompat.from(this)

        val notificationChannels = NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Student-Verse")
            .setContentText("$message")
            .setColor(Color.BLUE)
            .build()

        notificationManager.notify(1, notification)

    }
}
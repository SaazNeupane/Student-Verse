package com.example.studentverse.activity.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.studentverse.R
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.notification.NotificationChannels
import com.example.studentverse.activity.repository.UserRepository
import de.hdodenhof.circleimageview.CircleImageView
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
    private lateinit var change: TextView
    private lateinit var changepp: CircleImageView

    private var userDetails: User? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        etfname=findViewById(R.id.etufname)
        etlname=findViewById(R.id.etulname)
        etmobile=findViewById(R.id.etumobile)
        etaddress=findViewById(R.id.etuaddress)
        btnupdate=findViewById(R.id.btnupdatedetails)
        change=findViewById(R.id.changepassword)
        changepp=findViewById(R.id.changepp)

        change.setOnClickListener {
            startActivity(
                Intent(
                    this@UpdateProfileActivity,
                    ChangePasswordActivity::class.java
                )
            )
        }

        loaduser()

        //Image
        changepp.setOnClickListener {
            loadpopup()
        }

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
                        etlname.setText( "${userDetails!!.lname}")
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

    //Image Load
    private fun loadpopup(){
        val popupMenu = PopupMenu(this, changepp)
        popupMenu.menuInflater.inflate(R.menu.gallery_camera, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menuCamera ->
                    openCamera()
                R.id.menuGallery ->
                    openGallery()
            }
            true
        }
        popupMenu.show()
    }

    private var REQUEST_GALLERY_CODE = 0
    private var REQUEST_CAMERA_CODE = 1
    private var imageUrl: String? = null

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_GALLERY_CODE)
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CAMERA_CODE)
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
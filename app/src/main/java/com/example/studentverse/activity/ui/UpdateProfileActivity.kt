package com.example.studentverse.activity.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.studentverse.R
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.notification.NotificationChannels
import com.example.studentverse.activity.repository.UserRepository
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class UpdateProfileActivity : AppCompatActivity() {
    private lateinit var etfname: EditText
    private lateinit var etlname: EditText
    private lateinit var etmobile: EditText
    private lateinit var etaddress: EditText
    private lateinit var etpassword: EditText
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
        etpassword=findViewById(R.id.etupassword)
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

        //Image
        changepp.setOnClickListener {
            loadpopup()
        }

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
                        etlname.setText( "${userDetails!!.lname}")
                        etmobile.setText( "${userDetails!!.mobile}")
                        etaddress.setText( "${userDetails!!.address}")

                        val imagepath = "https://student-verse.herokuapp.com/userprofile/${userDetails!!.profilename}"
                        Glide.with(this@UpdateProfileActivity)
                            .load(imagepath)
                            .apply( RequestOptions()
                                .placeholder(R.drawable.profileicon)
                                .fitCenter())
                            .into(changepp)
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
        val password=etpassword.text.toString()

        val details = User(fname=fname,lname = lname,address = address,mobile = mobile,password = password)

        CoroutineScope(Dispatchers.IO).launch{
            try{
                val userRepository = UserRepository()
                val response = userRepository.update(details)
                if (response.success == true){
                    if (imageUrl != null) {
                        uploadImage()
                        withContext(Dispatchers.Main) {
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_GALLERY_CODE && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = contentResolver
                val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                imageUrl = cursor.getString(columnIndex)
                changepp.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
                cursor.close()
            } else if (requestCode == REQUEST_CAMERA_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                imageUrl = file!!.absolutePath
                changepp.setImageBitmap(BitmapFactory.decodeFile(imageUrl))
            }
        }

    }

    private fun uploadImage() {
        if (imageUrl != null) {
            val file = File(imageUrl!!)

            val extension = file.extension

            val reqFile =
                RequestBody.create(MediaType.parse("image/$extension"), file)

            val body =
                MultipartBody.Part.createFormData("picture", file.name, reqFile)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userRepository = UserRepository()
                    val response = userRepository.uploadImage( body)
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@UpdateProfileActivity, "Uploaded", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.d("Image Error: ", ex.localizedMessage)
                        Toast.makeText(this@UpdateProfileActivity,
                            ex.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


    private fun bitmapToFile(
        bitmap: Bitmap,
        fileNameToSave: String
    ): File? {
        var file: File? = null
        return try {
            file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    .toString() + File.separator + fileNameToSave
            )
            file.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
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
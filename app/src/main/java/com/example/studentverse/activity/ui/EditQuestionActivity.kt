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
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.notification.NotificationChannels
import com.example.studentverse.activity.repository.QuestionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditQuestionActivity : AppCompatActivity() {
    private lateinit var ettitle: EditText
    private lateinit var etbody: EditText
    private lateinit var ettags: EditText
    private lateinit var btnpost: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_question)

        ettitle = findViewById(R.id.etedittitle)
        etbody = findViewById(R.id.eteditbody)
        ettags = findViewById(R.id.etedittags)
        btnpost = findViewById(R.id.btnupdatequestion)

        val intent = intent.getParcelableExtra<Post>("epost")!!

        val tags = intent.tags.toString()
            .replace("[", "")
            .replace("]", "")

        ettitle.setText("${intent.title.toString()}")
        etbody.setText("${intent.body.toString()}")
        ettags.setText("$tags")

        btnpost.setOnClickListener {
            val title = ettitle.text.toString()
            val body = etbody.text.toString()
            val tag = ettags.text.toString()
            val tags: List<String> = tag.trim().splitToSequence(",").filter { it.isNotEmpty() }.toList()

            CoroutineScope(Dispatchers.IO).launch {
                try{
                    val questionRepository = QuestionRepository()
                    val response = questionRepository.updatepost(intent._id!!,title,body,tags)
                    if (response.success == true){
                        withContext(Dispatchers.Main){
                            showNotification("${response.message}")
                            startActivity(
                                Intent(
                                    this@EditQuestionActivity,
                                    DashboardActivity::class.java
                                )
                            )
                        }
                    }
                }
                catch (ex: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@EditQuestionActivity,
                            ex.toString(),
                            Toast.LENGTH_SHORT)
                            .show()
                    }
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
package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.AnswerAdapter
import com.example.studentverse.activity.model.Subject
import com.example.studentverse.activity.model.Topic
import com.example.studentverse.activity.repository.QuestionRepository
import com.example.studentverse.activity.repository.SubjectRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class TopicActivity : AppCompatActivity() {

    private lateinit var lvtopic: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        lvtopic=findViewById(R.id.lvtopic)

        val intent = intent.getParcelableExtra<Subject>("topic")!!

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val subjectRepository = SubjectRepository()
                val response = subjectRepository.gettopic(intent._id!!)
                if (response.success == true) {
                    val topic = response.data!!
                }

            }
            catch (ex: Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@TopicActivity,
                        "$ex , Here",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}
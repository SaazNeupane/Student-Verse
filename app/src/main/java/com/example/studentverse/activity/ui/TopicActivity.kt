package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.AnswerAdapter
import com.example.studentverse.activity.adapter.SubjectAdapter
import com.example.studentverse.activity.adapter.TopicAdapter
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

    private lateinit var rvtopic: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        rvtopic=findViewById(R.id.rvtopic)

        val intent = intent.getParcelableExtra<Subject>("subject")!!

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val subjectRepository = SubjectRepository()
                val response = subjectRepository.gettopic(intent._id!!)
                if (response.success == true) {
                    val topic = response.data!!
                    withContext(Dispatchers.Main) {
                        val topicAdapter = TopicAdapter(topic,intent._id,this@TopicActivity)
                        rvtopic.adapter = topicAdapter
                        rvtopic.layoutManager= LinearLayoutManager(this@TopicActivity, LinearLayoutManager.VERTICAL,false)
                    }
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
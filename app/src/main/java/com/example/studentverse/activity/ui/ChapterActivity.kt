package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.ChapterAdapter
import com.example.studentverse.activity.adapter.TopicAdapter
import com.example.studentverse.activity.model.Subject
import com.example.studentverse.activity.model.Topic
import com.example.studentverse.activity.repository.SubjectRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ChapterActivity : AppCompatActivity() {

    private lateinit var rvchapter: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter)

        rvchapter=findViewById(R.id.rvchapter)

        val topic = intent.getParcelableExtra<Topic>("topic")!!
        val subjectid = intent.getStringExtra("subjectid")!!

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val subjectRepository = SubjectRepository()
                val response = subjectRepository.getchapter(subjectid,topic._id!!)
                if (response.success == true) {
                    val chapter = response.data!!
                    withContext(Dispatchers.Main) {
                        val chapterAdapter = ChapterAdapter(chapter,this@ChapterActivity)
                        rvchapter.adapter = chapterAdapter
                        rvchapter.layoutManager= LinearLayoutManager(this@ChapterActivity, LinearLayoutManager.VERTICAL,false)
                    }
                }

            }
            catch (ex: Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ChapterActivity,
                        "$ex",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}
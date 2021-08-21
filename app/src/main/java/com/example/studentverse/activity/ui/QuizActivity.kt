package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.ChapterAdapter
import com.example.studentverse.activity.adapter.QuizAdapter
import com.example.studentverse.activity.model.Chapter
import com.example.studentverse.activity.model.Topic
import com.example.studentverse.activity.repository.SubjectRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class QuizActivity : AppCompatActivity() {

    private lateinit var rvquiz: RecyclerView
    private lateinit var tvquiztopic: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        rvquiz=findViewById(R.id.rvquiz)
        tvquiztopic=findViewById(R.id.quiztopic)

        val chapter = intent.getParcelableExtra<Chapter>("chapter")!!

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val subjectRepository = SubjectRepository()
                val response = subjectRepository.getquiz(chapter._id!!)
                if (response.success == true) {
                    val quiz = response.data!!
                    withContext(Dispatchers.Main) {
                        val quizAdapter = QuizAdapter(quiz,this@QuizActivity)
                        rvquiz.adapter = quizAdapter
                        rvquiz.layoutManager= LinearLayoutManager(this@QuizActivity, LinearLayoutManager.VERTICAL,false)
                    }
                }

            }
            catch (ex: Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@QuizActivity,
                        "$ex",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }



    }
}
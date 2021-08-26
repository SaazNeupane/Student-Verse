package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.QuizAdapter
import com.example.studentverse.activity.adapter.StudyQuizAdapter
import com.example.studentverse.activity.model.Chapter
import com.example.studentverse.activity.repository.SubjectRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ReadQuizActivity : AppCompatActivity() {

    private lateinit var rvstudyquiz: RecyclerView
    private lateinit var tvquizstudytopic: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_quiz)

        rvstudyquiz=findViewById(R.id.rvstudyquiz)
        tvquizstudytopic=findViewById(R.id.studytopic)

        val chapter = intent.getParcelableExtra<Chapter>("chapter")!!

        tvquizstudytopic.text = chapter.name

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val subjectRepository = SubjectRepository()
                val response = subjectRepository.getquiz(chapter._id!!)
                if (response.success == true) {
                    val quiz = response.data!!
                    quiz.shuffle()
                    withContext(Dispatchers.Main) {
                        val quizAdapter = StudyQuizAdapter(quiz,this@ReadQuizActivity)
                        rvstudyquiz.adapter = quizAdapter
                        rvstudyquiz.layoutManager= LinearLayoutManager(this@ReadQuizActivity, LinearLayoutManager.VERTICAL,false)
                    }
                }

            }
            catch (ex: Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ReadQuizActivity,
                        "$ex, activity here",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
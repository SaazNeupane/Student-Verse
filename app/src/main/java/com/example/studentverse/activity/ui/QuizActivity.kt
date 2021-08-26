package com.example.studentverse.activity.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import java.util.*

class QuizActivity : AppCompatActivity() {

    private lateinit var rvquiz: RecyclerView
    private lateinit var tvquiztopic: TextView
    private lateinit var timer: Chronometer
    private lateinit var btnquiz: Button

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        rvquiz=findViewById(R.id.rvquiz)
        tvquiztopic=findViewById(R.id.quiztopic)
        timer=findViewById(R.id.timer)
        btnquiz=findViewById(R.id.btnquiz)

        timer.start()



        val chapter = intent.getParcelableExtra<Chapter>("chapter")!!

        tvquiztopic.text = chapter.name

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val subjectRepository = SubjectRepository()
                val response = subjectRepository.getquiz(chapter._id!!)
                if (response.success == true) {
                    val quiz = response.data!!
                    quiz.shuffle()
                    withContext(Dispatchers.Main) {
                        val quizAdapter = QuizAdapter(quiz,this@QuizActivity,btnquiz, timer)
                        rvquiz.adapter = quizAdapter
                        rvquiz.layoutManager= LinearLayoutManager(this@QuizActivity, LinearLayoutManager.VERTICAL,false)
                    }
                }

            }
            catch (ex: Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@QuizActivity,
                        "$ex, activity here",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
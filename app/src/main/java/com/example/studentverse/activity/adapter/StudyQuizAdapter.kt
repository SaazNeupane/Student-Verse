package com.example.studentverse.activity.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Quiz
import com.example.studentverse.activity.repository.SubjectRepository
import com.example.studentverse.activity.ui.DashboardActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudyQuizAdapter(
    private val listquiz: ArrayList<Quiz>,
    private val context: Context,
): RecyclerView.Adapter<StudyQuizAdapter.StudyQuizHolder>() {
    class StudyQuizHolder(view: View) : RecyclerView.ViewHolder(view) {
        val question: TextView = view.findViewById(R.id.tvsquizquestion)
        val opt1: TextView = view.findViewById(R.id.tvsquizopt1)
        val opt2: TextView = view.findViewById(R.id.tvsquizopt2)
        val opt3: TextView = view.findViewById(R.id.tvsquizopt3)
        val opt4: TextView = view.findViewById(R.id.tvsquizopt4)
        val correct: TextView = view.findViewById(R.id.tvscorrecanswer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyQuizAdapter.StudyQuizHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.studyquizdesign, parent, false)
        return StudyQuizAdapter.StudyQuizHolder(view)
    }

    override fun onBindViewHolder(holder: StudyQuizAdapter.StudyQuizHolder, position: Int) {
        val quiz = listquiz[position]

        holder.question.text = "${quiz.question}"
        holder.opt1.text = "1. ${quiz.options?.get(0)}"
        holder.opt2.text = "2. ${quiz.options?.get(1)}"
        holder.opt3.text = "3. ${quiz.options?.get(2)}"
        holder.opt4.text = "4. ${quiz.options?.get(3)}"
        holder.correct.text = "Correct Answer is ${quiz.answer}"


    }

    override fun getItemCount(): Int {
        return listquiz.size
    }
}
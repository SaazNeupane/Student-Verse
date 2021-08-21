package com.example.studentverse.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Quiz

class QuizAdapter (
    private val listquiz: ArrayList<Quiz>,
    private val context: Context
):RecyclerView.Adapter<QuizAdapter.QuizHolder>() {
    class QuizHolder(view: View): RecyclerView.ViewHolder(view){
        val tvquizquestion: TextView = view.findViewById(R.id.tvquizquestion)
        val rgoptions: RadioGroup = view.findViewById(R.id.rgoption)
        val rbopt1: RadioButton = view.findViewById(R.id.rb1)
        val rbopt2: RadioButton = view.findViewById(R.id.rb2)
        val rbopt3: RadioButton = view.findViewById(R.id.rb3)
        val rbopt4: RadioButton = view.findViewById(R.id.rb4)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizAdapter.QuizHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quizdesign, parent,false)
        return QuizAdapter.QuizHolder(view)
    }

    override fun onBindViewHolder(holder: QuizAdapter.QuizHolder, position: Int) {
        val quiz = listquiz[position]

        holder.tvquizquestion.text = "${quiz.question}"
        holder.rbopt1.text = quiz.options?.get(0)
        holder.rbopt2.text = quiz.options?.get(1)
        holder.rbopt3.text = quiz.options?.get(2)
        holder.rbopt4.text = quiz.options?.get(3)

        val selectedOption: Int = holder.rgoptions!!.checkedRadioButtonId


    }

    override fun getItemCount(): Int {
        return listquiz.size
    }
}
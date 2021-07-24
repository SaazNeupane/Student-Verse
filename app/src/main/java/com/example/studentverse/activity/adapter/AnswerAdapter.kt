package com.example.studentverse.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Answer

class AnswerAdapter(
    private val listanswer: ArrayList<Answer>,
    private val context: Context
): RecyclerView.Adapter<AnswerAdapter.AnswerHolder>() {
    class AnswerHolder(view: View) : RecyclerView.ViewHolder(view) {
        val answer: TextView = view.findViewById(R.id.answer)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerAdapter.AnswerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.answerdesign, parent, false)
        return AnswerAdapter.AnswerHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerAdapter.AnswerHolder, position: Int) {
        val answer = listanswer[position]

        holder.answer.text=answer.text
    }

    override fun getItemCount(): Int {
        return listanswer.size
    }
}
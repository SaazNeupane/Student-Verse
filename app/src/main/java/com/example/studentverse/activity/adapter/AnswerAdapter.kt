package com.example.studentverse.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Answer
import com.example.studentverse.activity.model.Comment

class AnswerAdapter(
    private val listanswer: ArrayList<Answer>,
    private val context: Context
): RecyclerView.Adapter<AnswerAdapter.AnswerHolder>() {
    class AnswerHolder(view: View) : RecyclerView.ViewHolder(view) {
        val answer: TextView = view.findViewById(R.id.answer)
        val rvcomments: RecyclerView = view.findViewById(R.id.rvcomments)
        val tvcomments: TextView = view.findViewById(R.id.tvcomments)
        val llcomments: LinearLayout = view.findViewById(R.id.llcomments)
        val lladdcomments: LinearLayout = view.findViewById(R.id.lladdcomment)
        val tvreply: TextView = view.findViewById(R.id.tvreply)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerAdapter.AnswerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.answerdesign, parent, false)
        return AnswerAdapter.AnswerHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerAdapter.AnswerHolder, position: Int) {
        val answer = listanswer[position]

        holder.answer.text=answer.text
        val comments = answer.comment
        if (comments != null) {
            holder.tvcomments.setVisibility(View.VISIBLE)
            holder.tvcomments.setOnClickListener {
                holder.llcomments.visibility = View.VISIBLE
                holder.lladdcomments.visibility = View.GONE
            }

            holder.tvreply.setOnClickListener {
                holder.llcomments.visibility = View.GONE
                holder.lladdcomments.visibility = View.VISIBLE
            }
        }
        val commentAdapter = comments?.let { CommentAdapter(it, context) }
        holder.rvcomments.adapter = commentAdapter
        holder.rvcomments.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

    }

    override fun getItemCount(): Int {
        return listanswer.size
    }
}
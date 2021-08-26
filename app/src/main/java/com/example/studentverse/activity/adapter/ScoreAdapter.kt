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
import com.example.studentverse.activity.model.Score
import com.example.studentverse.activity.repository.SubjectRepository
import com.example.studentverse.activity.ui.DashboardActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScoreAdapter (
    private val listscore: ArrayList<Score>,
    private val context: Context,
): RecyclerView.Adapter<ScoreAdapter.ScoreHolder>() {
    class ScoreHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvscoresubject: TextView = view.findViewById(R.id.tvscoresubject)
        val myscore: TextView = view.findViewById(R.id.myscore)
        val mytime: TextView = view.findViewById(R.id.mytime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreAdapter.ScoreHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.scoredesign, parent, false)
        return ScoreAdapter.ScoreHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreAdapter.ScoreHolder, position: Int) {
        val score = listscore[position]

        holder.tvscoresubject.text = "Chapter: ${score.quizname}"
        holder.myscore.text = "${score.score}/10"
        holder.mytime.text = score.time



    }

    override fun getItemCount(): Int {
        return if (listscore.size < 11) {
            listscore.size
        } else{
            10
        }
    }
}
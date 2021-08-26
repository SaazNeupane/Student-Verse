package com.example.studentverse.activity.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Quiz
import com.example.studentverse.activity.ui.QuizActivity
import android.os.SystemClock
import android.content.DialogInterface
import android.content.Intent
import com.example.studentverse.activity.repository.SubjectRepository
import com.example.studentverse.activity.repository.UserRepository
import com.example.studentverse.activity.ui.DashboardActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class QuizAdapter (
    private val listquiz: ArrayList<Quiz>,
    private val context: Context,
    private val btnquiz: Button,
    private val timer: Chronometer
):RecyclerView.Adapter<QuizAdapter.QuizHolder>() {
    class QuizHolder(view: View): RecyclerView.ViewHolder(view){
        val tvquizquestion: TextView = view.findViewById(R.id.tvquizquestion)
        val rbopt1: RadioButton = view.findViewById(R.id.rb1)
        val rbopt2: RadioButton = view.findViewById(R.id.rb2)
        val rbopt3: RadioButton = view.findViewById(R.id.rb3)
        val rbopt4: RadioButton = view.findViewById(R.id.rb4)
    }
    private var correct: Int? = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizAdapter.QuizHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quizdesign, parent,false)
        return QuizAdapter.QuizHolder(view)
    }

    override fun onBindViewHolder(holder: QuizAdapter.QuizHolder, position: Int ) {
        val quiz = listquiz[position]

        holder.tvquizquestion.text = "${quiz.question}"
        holder.rbopt1.text = quiz.options?.get(0)
        holder.rbopt2.text = quiz.options?.get(1)
        holder.rbopt3.text = quiz.options?.get(2)
        holder.rbopt4.text = quiz.options?.get(3)

        holder.rbopt1.setOnClickListener {
            if (holder.rbopt1.text == quiz.answer.toString()){
                correct = correct?.plus(1)
            }
        }
        holder.rbopt2.setOnClickListener {
            if (holder.rbopt2.text == quiz.answer.toString()){
                correct = correct?.plus(1)
            }
        }
        holder.rbopt3.setOnClickListener {
            if (holder.rbopt3.text == quiz.answer.toString()){
                correct = correct?.plus(1)
            }
        }
        holder.rbopt4.setOnClickListener {
            if (holder.rbopt4.text == quiz.answer.toString()){
                correct = correct?.plus(1)
            }
        }

        btnquiz.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Are you Sure?")
            //set message for alert dialog
            builder.setMessage("You wanna submit the quiz?")
            builder.setIcon(R.drawable.alert)

            //performing positive action
            builder.setPositiveButton("Yes"){ _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val subjectRepository = SubjectRepository()
                        val response = subjectRepository.addscore(correct.toString(), quiz.name!!,timer.text.toString())
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                val intent = Intent(context, DashboardActivity::class.java)
                                context.startActivity(intent)
                            }
                        }
                    } catch (ex: java.lang.Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                "Error : $ex", Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
            //performing negative action
            builder.setNegativeButton("No"){ _, _ ->
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

    }

    override fun getItemCount(): Int {
        return if (listquiz.size < 11) {
            listquiz.size
        } else{
            10
        }
    }
}
package com.example.studentverse.activity.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.repository.QuestionRepository
import com.example.studentverse.activity.repository.SubjectRepository
import com.example.studentverse.activity.ui.DashboardActivity
import com.example.studentverse.activity.ui.EditQuestionActivity
import com.example.studentverse.activity.ui.SinglePostActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyQuestionAdapter (
    private val  context: Context,
    private val PostList: MutableList<Post>

):
RecyclerView.Adapter<MyQuestionAdapter.MyQuestionViewHolder>(){
    class MyQuestionViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.edittitle)
        val answercount: TextView = view.findViewById(R.id.myanswercount)
        val lledit: LinearLayout = view.findViewById(R.id.lledit)
        val lldelete: LinearLayout = view.findViewById(R.id.lldelete)
        val llview: LinearLayout = view.findViewById(R.id.llview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyQuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.questiondesign,parent,false)
        return MyQuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyQuestionViewHolder, position: Int) {
        val post = PostList[position]
        holder.title.text=post.title

        holder.lledit.setOnClickListener {
            val intent = Intent(context, EditQuestionActivity::class.java)
                .putExtra("epost",post)
            context.startActivity(intent)
        }
        holder.lldelete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Are you Sure?")
            //set message for alert dialog
            builder.setMessage("You wanna submit the quiz?")
            builder.setIcon(R.drawable.alert)

            //performing positive action
            builder.setPositiveButton("Yes"){ _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val questionRepository = QuestionRepository()
                        val response = questionRepository
//                        if (response.success == true) {
//                            withContext(Dispatchers.Main) {
//                                val intent = Intent(context, DashboardActivity::class.java)
//                                context.startActivity(intent)
//                            }
//                        }
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
        holder.answercount.text = post.answer?.size.toString()

        holder.llview.setOnClickListener {
            val intent = Intent(context, SinglePostActivity::class.java)
                .putExtra("post",post)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return PostList.size
    }
}
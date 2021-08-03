package com.example.studentverse.activity.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Answer
import com.example.studentverse.activity.model.Comment
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.model.Vote
import com.example.studentverse.activity.repository.QuestionRepository
import com.example.studentverse.activity.ui.SinglePostActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AnswerAdapter(
    private val listanswer: ArrayList<Answer>,
    private val question: Post,
    private val userid: String,
    private val context: Context,
): RecyclerView.Adapter<AnswerAdapter.AnswerHolder>() {
    class AnswerHolder(view: View) : RecyclerView.ViewHolder(view) {
        val answer: TextView = view.findViewById(R.id.answer)
        val rvcomments: RecyclerView = view.findViewById(R.id.rvcomments)
        val tvcomments: TextView = view.findViewById(R.id.tvcomments)
        val llcomments: LinearLayout = view.findViewById(R.id.llcomments)
        val lladdcomments: LinearLayout = view.findViewById(R.id.lladdcomment)
        val tvreply: TextView = view.findViewById(R.id.tvreply)
        val tvscore: TextView = view.findViewById(R.id.score)
        val etccomment: EditText = view.findViewById(R.id.etccomment)
        val btnccomment: ImageButton = view.findViewById(R.id.btnccomment)
        val upvote: ImageButton = view.findViewById(R.id.upvote)
        val downvote: ImageButton = view.findViewById(R.id.downvote)
    }
    private var upclicked : Boolean? = null
    private var downclicked : Boolean? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerAdapter.AnswerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.answerdesign, parent, false)
        return AnswerAdapter.AnswerHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerAdapter.AnswerHolder, position: Int) {
        val answer = listanswer[position]
        holder.answer.text=answer.text
        holder.tvscore.text = answer.score.toString()
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

        val votes = answer.votes
        val size = votes?.size

        for (i in 0 until size!!){
            if(votes[i].user == userid && votes[i].vote == 1){
                print(votes[i])
                upclicked = false
                downclicked = true
            }
            else if (votes[i].user == userid && votes[i].vote == -1){
                upclicked = true
                downclicked = false
            }
            else{
                upclicked = true;
                downclicked = true;
            }
            println(upclicked)
            println(downclicked)
        }
        holder.upvote.setOnClickListener {
            if (upclicked!!){
                upclicked = false;
                downclicked = true;
                val vote = Vote(answer = answer._id, post = question._id)
                CoroutineScope(Dispatchers.IO).launch {
                    try{
                        val questionRepository = QuestionRepository()
                        val response = questionRepository.upvote(vote)
                        if (response.success == true){
                            withContext(Dispatchers.Main){
                                Toast.makeText(context, "${response.message}", Toast.LENGTH_SHORT).show()
                                val intent = Intent(context, SinglePostActivity::class.java)
                                context.startActivity(intent)
                            }
                        }
                    }
                    catch (ex: Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(context,
                                ex.toString(),
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
            else{
                upclicked = true;
                val vote = Vote(answer = answer._id, post = question._id)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val questionRepository = QuestionRepository()
                        val response = questionRepository.unvote(vote)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(context, "${response.message}", Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(context, SinglePostActivity::class.java)
                                context.startActivity(intent)
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                ex.toString(),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
        }
        holder.downvote.setOnClickListener {
            if (downclicked!!){
                downclicked = false;
                upclicked = true;
                val vote = Vote(answer = answer._id, post = question._id)
                CoroutineScope(Dispatchers.IO).launch {
                    try{
                        val questionRepository = QuestionRepository()
                        val response = questionRepository.downvote(vote)
                        if (response.success == true){
                            withContext(Dispatchers.Main){
                                Toast.makeText(context, "${response.message}", Toast.LENGTH_SHORT).show()
                                val intent = Intent(context, SinglePostActivity::class.java)
                                    .putExtra("post",question)
                                context.startActivity(intent)
                            }
                        }
                    }
                    catch (ex: Exception){
                        withContext(Dispatchers.Main){
                            Toast.makeText(context,
                                ex.toString(),
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
            else{
                downclicked = true;
                val vote = Vote(answer = answer._id, post = question._id)
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val questionRepository = QuestionRepository()
                        val response = questionRepository.unvote(vote)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(context, "${response.message}", Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(context, SinglePostActivity::class.java)
                                context.startActivity(intent)
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                ex.toString(),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
        }

        holder.btnccomment.setOnClickListener {
            val comt = holder.etccomment.text.toString()

            val comment = Comment(text = comt, answer = answer._id, question = question._id )

            CoroutineScope(Dispatchers.IO).launch {
                try{
                    val questionRepository = QuestionRepository()
                    val response = questionRepository.addcomment(comment)
                    if (response.success == true){
                        withContext(Dispatchers.Main){
                            Toast.makeText(context, "Comment Added", Toast.LENGTH_SHORT).show()
                            val intent = Intent(context, SinglePostActivity::class.java)
                                .putExtra("post",question)
                            context.startActivity(intent)
                        }
                    }
                }
                catch (ex: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(context,
                            ex.toString(),
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return listanswer.size
    }
}
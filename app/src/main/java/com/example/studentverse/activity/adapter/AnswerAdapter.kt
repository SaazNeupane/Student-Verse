package com.example.studentverse.activity.adapter

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.*
import com.example.studentverse.activity.repository.QuestionRepository
import com.example.studentverse.activity.repository.UserRepository
import com.example.studentverse.activity.ui.SinglePostActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AnswerAdapter(
    private val listanswer: ArrayList<Answer>,
    private val question: Post,
    private val context: Context,
): RecyclerView.Adapter<AnswerAdapter.AnswerHolder>() {
    class AnswerHolder(view: View) : RecyclerView.ViewHolder(view) {
        val answer: TextView = view.findViewById(R.id.answer)
        val rvcomments: RecyclerView = view.findViewById(R.id.rvcomments)
        val tvcomments: TextView = view.findViewById(R.id.tvcomments)
        val tvausername: TextView = view.findViewById(R.id.tvausername)
        val llcomments: LinearLayout = view.findViewById(R.id.llcomments)
        val lladdcomments: LinearLayout = view.findViewById(R.id.lladdcomment)
        val tvreply: TextView = view.findViewById(R.id.tvreply)
        val tvatime: TextView = view.findViewById(R.id.tvatime)
        val tvscore: TextView = view.findViewById(R.id.score)
        val etccomment: EditText = view.findViewById(R.id.etccomment)
        val btnccomment: ImageButton = view.findViewById(R.id.btnccomment)
        val upvote: ImageButton = view.findViewById(R.id.upvote)
        val downvote: ImageButton = view.findViewById(R.id.downvote)
    }
    private var upclicked : Boolean = true
    private var downclicked : Boolean = true
    private var userid: String = ""
    private var userDetails: User? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerAdapter.AnswerHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.answerdesign, parent, false)
        return AnswerAdapter.AnswerHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerAdapter.AnswerHolder, position: Int) {
        val answer = listanswer[position]
        holder.answer.text=answer.text
        holder.tvscore.text = answer.score.toString()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val parsedDate: Date = dateFormat.parse(answer.createdAt)
        val print = SimpleDateFormat("d MMM, yyyy")
        holder.tvatime.text = "${print.format(parsedDate)}"
        val comments = answer.comment
        if (comments != null) {
            holder.tvcomments.visibility = View.VISIBLE
            holder.tvcomments.text = "${answer.comment.size.toString()} replies......."
            holder.tvcomments.setOnClickListener {
                if (holder.llcomments.visibility == View.VISIBLE){
                    holder.llcomments.visibility = View.GONE
                }
                else{
                    holder.llcomments.visibility = View.VISIBLE
                    holder.lladdcomments.visibility = View.GONE
                }
            }

            holder.tvreply.setOnClickListener {
                if (holder.lladdcomments.visibility == View.VISIBLE){
                    holder.lladdcomments.visibility = View.GONE
                }
                else {
                    holder.llcomments.visibility = View.GONE
                    holder.lladdcomments.visibility = View.VISIBLE
                }
            }
        }
        val commentAdapter = comments?.let { CommentAdapter(it, context) }
        holder.rvcomments.adapter = commentAdapter
        holder.rvcomments.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        val votes = answer.votes
        val size = votes?.size

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.finduser(answer.author!!)

                if (response.success == true) {
                    userDetails = response.data!!
                    withContext(Dispatchers.Main) {
                        holder.tvausername.text = "- ${userDetails!!.username.toString()}"
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

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.profile()

                if (response.success == true) {
                    userDetails = response.data!!
                    userid= userDetails!!._id.toString()
                    withContext(Dispatchers.Main) {
                        for (i in 0 until size!!){
                            if(votes[i].user == userid && votes[i].vote == 1){
                                holder.upvote.setImageResource(R.drawable.after_upvote);
                                upclicked = false
                                downclicked = true
                            }
                            else if (votes[i].user == userid && votes[i].vote == -1){
                                holder.downvote.setImageResource(R.drawable.after_downvote)
                                upclicked = true
                                downclicked = false
                            }
                            else{
                                upclicked = true
                                downclicked = true
                            }
                        }
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

        holder.upvote.setOnClickListener {
            if (upclicked!!){
                holder.upvote.setImageResource(R.drawable.after_upvote);
                holder.downvote.setImageResource(R.drawable.ic_baseline_arrow_circle_down_24)
                holder.tvscore.text = (holder.tvscore.text.toString().toInt() + 1).toString()
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
//                                val intent = Intent(context, SinglePostActivity::class.java)
//                                    .putExtra("post",question)
//                                context.startActivity(intent)
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
                holder.upvote.setImageResource(R.drawable.ic_outline_arrow_circle_up_24)
                holder.tvscore.text = (holder.tvscore.text.toString().toInt() - 1).toString()
                upclicked = true;
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
//                                val intent = Intent(context, SinglePostActivity::class.java)
//                                    .putExtra("post",question)
//                                context.startActivity(intent)
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
                holder.downvote.setImageResource(R.drawable.after_downvote)
                holder.upvote.setImageResource(R.drawable.ic_outline_arrow_circle_up_24)
                holder.tvscore.text = (holder.tvscore.text.toString().toInt() - 1).toString()
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
//                                val intent = Intent(context, SinglePostActivity::class.java)
//                                    .putExtra("post",question)
//                                context.startActivity(intent)
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
                holder.downvote.setImageResource(R.drawable.ic_baseline_arrow_circle_down_24)
                holder.tvscore.text = (holder.tvscore.text.toString().toInt() + 1).toString()
                downclicked = true;
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
//                                val intent = Intent(context, SinglePostActivity::class.java)
//                                    .putExtra("post",question)
//                                context.startActivity(intent)
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

            if(TextUtils.isEmpty(holder.etccomment.text)) {
                holder.etccomment.error = "Please comment on answer"
                holder.etccomment.requestFocus()
            }
            else{

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
    }

    override fun getItemCount(): Int {
        return listanswer.size
    }
}
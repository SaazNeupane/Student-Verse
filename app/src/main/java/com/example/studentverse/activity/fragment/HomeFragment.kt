package com.example.studentverse.activity.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.QuestionAdapter
import com.example.studentverse.activity.repository.QuestionRepository
import com.example.studentverse.activity.ui.AskQuestionActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var rvques: RecyclerView
    private lateinit var tvqcount: TextView
    private lateinit var btnhask: Button
    private lateinit var swipe: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_home, container, false)

        //Recyler View
        rvques=view.findViewById(R.id.rvques)
        tvqcount=view.findViewById(R.id.tvqcount)
        btnhask=view.findViewById(R.id.btnhask)
        swipe=view.findViewById(R.id.swipetorefresh)
        loadallquestion()

        swipe.setOnRefreshListener {

            swipe.postDelayed( Runnable {
                swipe()
                if (swipe.isRefreshing){
                    swipe.isRefreshing = false
                }
            },500)
        }

        btnhask.setOnClickListener {
            startActivity(
                Intent(
                    context,
                    AskQuestionActivity::class.java)
            )
        }
        return view
    }

    private fun loadallquestion(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val questionRepository = QuestionRepository()
                val response = questionRepository.allquestion()
                if (response.success == true) {
                    val question = response.data!!
                    withContext(Main) {
                        tvqcount.text = "${question.size} questions"
                        val questionAdapter = QuestionAdapter(requireContext(),question)
                        rvques.adapter = questionAdapter
                        rvques.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    }
                }
            }
            catch (ex:Exception){
                withContext(Main) {
                    Toast.makeText(context,
                        "$ex .toString()",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun swipe(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val questionRepository = QuestionRepository()
                val response = questionRepository.allquestion()
                if (response.success == true) {
                    val question = response.data!!
                    withContext(Main) {
                        tvqcount.text = "${question.size} questions"
                        val questionAdapter = QuestionAdapter(requireContext(),question)
                        rvques.adapter = questionAdapter
                        rvques.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    }
                }
            }
            catch (ex:Exception){
                withContext(Main) {
                    Toast.makeText(context,
                        "$ex .toString()",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
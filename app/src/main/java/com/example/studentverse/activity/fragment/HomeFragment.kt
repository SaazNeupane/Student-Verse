package com.example.studentverse.activity.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.QuestionAdapter
import com.example.studentverse.activity.repository.QuestionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var rvques: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_home, container, false)

        loadallquestion()
        return view
    }

    private fun loadallquestion(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val questionRepository = QuestionRepository()
                val response = questionRepository.allquestion()
                if (response.success == true) {
                    val question = response.data!!
                    withContext(Dispatchers.Main) {
                        val adAdapter = QuestionAdapter(context!!,question)
                        rvques.adapter = adAdapter
                        rvques.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(context,
                        ex.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
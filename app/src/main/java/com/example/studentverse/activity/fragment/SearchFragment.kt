package com.example.studentverse.activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.QuestionAdapter
import com.example.studentverse.activity.adapter.UserAdapter
import com.example.studentverse.activity.repository.QuestionRepository
import com.example.studentverse.activity.repository.SubjectRepository
import com.example.studentverse.activity.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {
    private lateinit var spoptions: Spinner
    private lateinit var etsearch: EditText
    private lateinit var ntf: TextView
    private lateinit var tvscount: TextView
    private lateinit var btnsearch: Button
    private lateinit var rvsearch: RecyclerView
    private var selectedItem= ""
    private val options= arrayOf(
        "Questions","Tags","User"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_search, container, false)

        spoptions = view.findViewById(R.id.spoptions)
        ntf = view.findViewById(R.id.ntf)
        tvscount = view.findViewById(R.id.tvscount)
        rvsearch = view.findViewById(R.id.rvsearch)
        etsearch = view.findViewById(R.id.etsearch)
        btnsearch = view.findViewById(R.id.btnsearch)
        loadallquestion()
        spoptions.adapter = ArrayAdapter(
            context!!,
            R.layout.support_simple_spinner_dropdown_item,options
        )

        spoptions.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long) {
                    selectedItem = parent?.getItemAtPosition(position).toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }


        btnsearch.setOnClickListener {
            search()
        }

        return view
    }

    private fun search(){
        val category = selectedItem
        val searchtext =etsearch.text.toString()

        if(category == "Questions"){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val subjectRepository = SubjectRepository()
                    val response = subjectRepository.searchquestion(searchtext)
                    if (response.success == true) {
                        val question = response.data!!
                        withContext(Dispatchers.Main) {
                            ntf.visibility = View.GONE
                            rvsearch.visibility = View.VISIBLE
                            tvscount.visibility = View.VISIBLE
                            tvscount.text = "Found ${question.size} questions"
                            val questionAdapter = QuestionAdapter(context!!,question)
                            rvsearch.adapter = questionAdapter
                            rvsearch.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                        }
                    }
                }
                catch (ex:Exception){
                    withContext(Dispatchers.Main) {
                        ntf.visibility = View.VISIBLE
                        rvsearch.visibility = View.GONE
                        tvscount.visibility = View.GONE
                    }
                }
            }
        }

        else if(category == "Tags"){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val subjectRepository = SubjectRepository()
                    val response = subjectRepository.searchtag(searchtext)
                    if (response.success == true) {
                        val question = response.data!!
                            withContext(Dispatchers.Main) {
                                ntf.visibility = View.GONE
                                rvsearch.visibility = View.VISIBLE
                                tvscount.visibility = View.VISIBLE
                                tvscount.text = "Found ${question.size} questions"
                                val questionAdapter = QuestionAdapter(context!!,question)
                                rvsearch.adapter = questionAdapter
                                rvsearch.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                            }
                    }
                }
                catch (ex:Exception){
                    withContext(Dispatchers.Main) {
                        ntf.visibility = View.VISIBLE
                        rvsearch.visibility = View.GONE
                        tvscount.visibility = View.GONE
                    }
                }
            }
        }

        else if(category == "User"){
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userRepository = UserRepository()
                    val response = userRepository.searchuser(searchtext)
                    if (response.success == true) {
                        val user = response.data!!
                            withContext(Dispatchers.Main) {
                                ntf.visibility = View.GONE
                                rvsearch.visibility = View.VISIBLE
                                tvscount.visibility = View.VISIBLE
                                tvscount.text = "Found ${user.size} Users"
                                val userAdapter = UserAdapter(user,context!!)
                                rvsearch.adapter = userAdapter
                                rvsearch.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

                            }
                    }
                }
                catch (ex:Exception){
                    withContext(Dispatchers.Main) {
                        ntf.visibility = View.VISIBLE
                        rvsearch.visibility = View.GONE
                        tvscount.visibility = View.GONE
                    }
                }
            }
        }

    }

    private fun loadallquestion(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val questionRepository = QuestionRepository()
                val response = questionRepository.allquestion()
                if (response.success == true) {
                    val question = response.data!!
                    withContext(Dispatchers.Main) {
                        val questionAdapter = QuestionAdapter(context!!,question)
                        rvsearch.adapter = questionAdapter
                        rvsearch.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(context,
                        "$ex .toString()",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
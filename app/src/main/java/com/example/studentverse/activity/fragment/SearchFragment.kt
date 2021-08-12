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
import com.example.studentverse.activity.repository.QuestionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment() {
    private lateinit var spoptions: Spinner
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
        rvsearch = view.findViewById(R.id.rvsearch)
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
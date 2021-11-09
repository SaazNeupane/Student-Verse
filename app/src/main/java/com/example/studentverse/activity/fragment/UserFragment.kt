package com.example.studentverse.activity.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.MyQuestionAdapter
import com.example.studentverse.activity.adapter.QuestionAdapter
import com.example.studentverse.activity.adapter.ScoreAdapter
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.repository.QuestionRepository
import com.example.studentverse.activity.repository.UserRepository
import com.example.studentverse.activity.ui.AskQuestionActivity
import com.example.studentverse.activity.ui.UpdateProfileActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserFragment : Fragment() {

    private lateinit var btnask: Button
    private lateinit var btnlogout: Button
    private lateinit var btnmore: ImageButton
    private lateinit var name: TextView
    private lateinit var mobile: TextView
    private lateinit var email: TextView
    private lateinit var ntf: TextView
    private lateinit var ntf2: TextView
    private lateinit var profileimage: ImageView
    private lateinit var rvmyques: RecyclerView
    private lateinit var rvquizscore: RecyclerView

    var userDetails: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_user, container, false)

        btnask = view.findViewById(R.id.btnask)
        btnlogout = view.findViewById(R.id.btnlogout)
        btnmore = view.findViewById(R.id.btnmore)
        name = view.findViewById(R.id.name)
        ntf = view.findViewById(R.id.ntf)
        ntf2 = view.findViewById(R.id.ntf2)
        mobile = view.findViewById(R.id.mobile)
        email = view.findViewById(R.id.email)
        profileimage=view.findViewById(R.id.profileimage)
        rvmyques=view.findViewById(R.id.rvmyques)
        rvquizscore=view.findViewById(R.id.rvquizscore)
        loadclient()

        btnask.setOnClickListener {
            startActivity(
                    Intent(
                            context,
                            AskQuestionActivity::class.java)
            )
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val questionRepository = QuestionRepository()
                val response = questionRepository.myquestions()
                if (response.success == true) {
                    ntf.visibility = View.GONE
                    rvmyques.visibility = View.VISIBLE
                    val question = response.data!!
                    withContext(Dispatchers.Main) {
                        val questionAdapter = MyQuestionAdapter(requireContext(),question)
                        rvmyques.adapter = questionAdapter
                        rvmyques.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main) {
                    rvmyques.visibility = View.GONE
                    ntf.visibility = View.VISIBLE
                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val questionRepository = QuestionRepository()
                val response = questionRepository.myscore()
                    if (response.success == true) {
                        ntf2.visibility = View.GONE
                        rvquizscore.visibility = View.VISIBLE
                        val score = response.data!!
                        withContext(Dispatchers.Main) {
                            val questionAdapter = ScoreAdapter(score,requireContext())
                            rvquizscore.adapter = questionAdapter
                            rvquizscore.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                        }
                    }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main) {
                    rvquizscore.visibility = View.GONE
                    ntf2.visibility = View.VISIBLE
                }
            }
        }

        btnlogout.setOnClickListener {
            val sharedPref = activity?.getSharedPreferences("Preferences", AppCompatActivity.MODE_PRIVATE)
            val editor = sharedPref?.edit()
            editor?.clear()
            editor?.apply()
            ServiceBuilder.token.equals("")
            activity?.finish()
        }
        btnmore.setOnClickListener{
            loadoption()
        }

        return view
    }
    private fun loadclient() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.profile()

                if (response.success == true) {
                    userDetails = response.data!!
                    withContext(Dispatchers.Main) {
                        name.text = "Welcome, ${userDetails!!.username}"
                        mobile.text = "Mobile: ${userDetails!!.mobile}"
                        email.text = "Email: ${userDetails!!.email}"
                        val imagepath = "https://student-verse.herokuapp.com/userprofile/${userDetails!!.profilename}"
                        Glide.with(requireContext())
                            .load(imagepath)
                            .apply( RequestOptions()
                                .placeholder(R.drawable.profileicon)
                                .fitCenter())
                            .into(profileimage)
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Error : $ex", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    //Option Load
    private fun loadoption(){
        val popupMenu = PopupMenu(requireContext(), btnmore)
        popupMenu.menuInflater.inflate(R.menu.option, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.updateprofile ->
                    startActivity(
                        Intent(
                            context,
                            UpdateProfileActivity::class.java)
                    )
            }
            true
        }
        popupMenu.show()
    }

}
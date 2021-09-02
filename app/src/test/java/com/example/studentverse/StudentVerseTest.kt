package com.example.studentverse

import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.model.Answer
import com.example.studentverse.activity.model.Comment
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.repository.QuestionRepository
import com.example.studentverse.activity.repository.SubjectRepository
import com.example.studentverse.activity.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class StudentVerseTest {
    private lateinit var userRepository: UserRepository
    private lateinit var questionRepository: QuestionRepository
    private lateinit var subjectRepository: SubjectRepository

    @Test
    fun registerUser() = runBlocking {
        userRepository = UserRepository()
        val user =
            User(fname = "abc", lname = "xyz", email = "abc@gmail.com", username = "abc", mobile = "9898", password = "abc", address = "abc")

        val response = userRepository.userRegister(user)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }


    @Test
    fun loginUser() = runBlocking{
        userRepository = UserRepository()
        val response = userRepository.checkuser("saazneu789","12345")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun fetchuser() = runBlocking {
        userRepository = UserRepository()

        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token
        val response = userRepository.profile()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun changepassword() = runBlocking {
        userRepository = UserRepository()

        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token
        val response = userRepository.changepassword("12345","123456")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun updateprofile() = runBlocking {
        userRepository = UserRepository()

        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token
        val user =
            User(fname = "Saaz", lname = "Neupane", address = "koteshwor", mobile = "9861320772", password = "12345")

        val response = userRepository.update(user)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun fetchotheruser() = runBlocking {
        userRepository = UserRepository()

        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token
        val response = userRepository.finduser("60ec05eba750837f88672d14")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun addQuestion() = runBlocking {
        userRepository = UserRepository()
        questionRepository = QuestionRepository()
        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token
        val tags: List<String> = listOf("name","science")
        val post = Post(title = "I HAVE A QUERY",body = "My Science text is not happening",tags = tags)
        val response = questionRepository.addpost(post)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun updatequestion() = runBlocking {
        userRepository = UserRepository()
        questionRepository = QuestionRepository()
        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token
        val tags: List<String> = listOf("name","science")
        val response = questionRepository.updatepost("61285a9836f52e4230cecec8","I HAVE A QUERY","My Science text is not displaying",tags)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun deletequestion() = runBlocking {
        userRepository = UserRepository()
        questionRepository = QuestionRepository()
        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token

        val response = questionRepository.deletepost("61285a9836f52e4230cecec8")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun addAnswer() = runBlocking {
        userRepository = UserRepository()
        questionRepository = QuestionRepository()

        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token

        val answer = Answer(post = "61285a9836f52e4230cecec8", text = "I have your answer")
        val response = questionRepository.answeradd(answer)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun addComment() = runBlocking {
        userRepository = UserRepository()
        questionRepository = QuestionRepository()

        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token

        val comment = Comment(question = "61285a9836f52e4230cecec8",answer = "61285ac336f52e4230ceced0",text = "So, can you tell me?")
        val response = questionRepository.addcomment(comment)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun fetchQuestion() = runBlocking {
        userRepository = UserRepository()

        questionRepository = QuestionRepository()
        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token
        val response = questionRepository.allquestion()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun fetchAnswer() = runBlocking {
        userRepository = UserRepository()

        questionRepository = QuestionRepository()
        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token
        val response = questionRepository.getanswer(id = "61285a9836f52e4230cecec8")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun searchPost() = runBlocking {
        questionRepository = QuestionRepository()
        val response = questionRepository.searchquestion("science")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun searchtags() = runBlocking {
        questionRepository = QuestionRepository()
        val response = questionRepository.searchtag("science")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun searchuser() = runBlocking {
        userRepository = UserRepository()
        val response = userRepository.searchuser("saaz")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun fetchsubject() = runBlocking {
        userRepository = UserRepository()
        subjectRepository = SubjectRepository()

        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token
        val response = subjectRepository.allsubjects()
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun fetchtopic() = runBlocking {
        userRepository = UserRepository()
        subjectRepository = SubjectRepository()

        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token
        val response = subjectRepository.gettopic("610e84a624e8a04e989f44f1")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun fetchchapter() = runBlocking {
        userRepository = UserRepository()
        subjectRepository = SubjectRepository()

        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token
        val response = subjectRepository.getchapter("610e84a624e8a04e989f44f1","61118079f52e654158fb96cf")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }


    @Test
    fun fetchquiz() = runBlocking {
        subjectRepository = SubjectRepository()
        val response = subjectRepository.getquiz("6117d7361cc596000412208a")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

}
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
    fun addAnswer() = runBlocking {
        userRepository = UserRepository()
        questionRepository = QuestionRepository()

        ServiceBuilder.token ="Bearer "+ userRepository.checkuser("saazneu789","12345").token

        val answer = Answer(post = "6103b66f0624ab17145fc4a7", text = "I have your answer")
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

        val comment = Comment(question = "6103b66f0624ab17145fc4a7",answer = "6103b7df0624ab17145fc4ac",text = "So, can you tell me?")
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
        val response = questionRepository.getanswer(id = "6103b66f0624ab17145fc4a7")
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }

    @Test
    fun searchPost() = runBlocking {
        subjectRepository = SubjectRepository()
        val searchText = "java"
        val response = subjectRepository.searchquestion(searchText)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult,actualResult)
    }
}
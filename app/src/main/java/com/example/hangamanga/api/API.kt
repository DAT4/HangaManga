package com.example.hangamanga.api

import com.example.hangamanga.models.HighScore
import com.example.hangamanga.models.Token
import com.example.hangamanga.models.User
import com.example.hangamanga.models.Word
import retrofit2.Response
import retrofit2.http.*

interface API {
    @GET("/hangman/")
    suspend fun getWords(): Response<List<Word>>

    @GET("/hangman/score")
    suspend fun getScores(): Response<List<HighScore>>

    @Headers("Content-Type: application/json")
    @POST("/hangman/login")
    suspend fun login(@Body user: User) : Response<Token>

    @Headers("Content-Type: application/json")
    @POST("/hangman/score")
    suspend fun postScore(@Header("Authorization") token:String, @Body score: HighScore)
}
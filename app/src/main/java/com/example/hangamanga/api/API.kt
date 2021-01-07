package com.example.hangamanga.api

import com.example.hangamanga.models.HighScore
import com.example.hangamanga.models.Word
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET("/hangman/")
    suspend fun getWords() : Response<List<Word>>
    @GET("/hangman/score")
    suspend fun getScores() : Response<List<HighScore>>
}
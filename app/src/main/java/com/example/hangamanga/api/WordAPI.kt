package com.example.hangamanga.api

import com.example.hangamanga.models.HighScore
import com.example.hangamanga.models.Word
import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("/hangman/api")
    fun getWords() : Call<List<Word>>
    @GET("/hangman/api/highscores")
    fun getScores() : Call<List<HighScore>>
}
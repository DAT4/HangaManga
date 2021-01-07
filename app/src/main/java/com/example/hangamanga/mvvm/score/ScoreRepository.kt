package com.example.hangamanga.mvvm.score

import com.example.hangamanga.api.RetrofitInstance

class ScoreRepository(private val db: ScoreDatabase) {
    suspend fun getScores() = RetrofitInstance.api.getScores()
}


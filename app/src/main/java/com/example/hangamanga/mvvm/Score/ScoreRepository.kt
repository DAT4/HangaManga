package com.example.hangamanga.mvvm.Score

import com.example.hangamanga.api.RetrofitInstance

class ScoreRepository(private val db: ScoreDatabase) {
    fun getScores() = RetrofitInstance.api.getScores()
}


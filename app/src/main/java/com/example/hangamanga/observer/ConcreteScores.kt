package com.example.hangamanga.observer

import com.google.gson.reflect.TypeToken
import com.example.hangamanga.models.HighScore
import com.example.hangamanga.models.Word
import java.lang.reflect.Type

object ConcreteScores : ICache<ArrayList<HighScore>> {
    override val url: String = "https://mama.sh/hangman/api/"
    override val type: Type = object : TypeToken<ArrayList<HighScore>>() {}.type

    override var content: ArrayList<HighScore> = ArrayList()
    override val observers: ArrayList<IObserver> = ArrayList()

    fun getHighScoreFromWord(word: Word): List<HighScore> {
        val scores: ArrayList<HighScore> = ArrayList()
        content.forEach { score ->
            if (score is HighScore)
                if (score.word.ID == word.ID) {
                    scores.add(score)
                }
        }
        return order(scores)
    }

    fun getCategories(): List<Pair<String,List<HighScore>>>{
        return content.groupBy {
            it.word.category
        }.toList()
    }

    fun getHighScoreFromCategory(category: String): List<HighScore> {
        val scores: ArrayList<HighScore> = ArrayList()
        content.forEach { score ->
            if (score is HighScore)
                if (score.word.category == category) {
                    scores.add(score)
                }
        }
        return order(scores)
    }

    fun getOverAllHighScores(): List<HighScore> {
        return order(content as ArrayList<HighScore>)
    }

    fun setHighScores(scores: ArrayList<HighScore>) {
        content = scores
        sendUpdateEvent()
    }

    private fun order(scores: ArrayList<HighScore>): List<HighScore> {
        scores
            .sortWith(kotlin.Comparator { lhs, rhs ->
            when {
                lhs.getScore() > rhs.getScore() -> -1
                lhs.getScore() < rhs.getScore() -> 1
                else -> 0
            }
        })

        return if (scores.size > 10) {
            scores.slice(0 until 10)
        } else {
            scores
        }
    }
}
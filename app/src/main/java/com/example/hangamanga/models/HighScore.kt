package com.example.hangamanga.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HighScore(
    val ID: String? = null,
    val player: String,
    val time: Int,
    val hints: Int,
    val wrongs: Int,
    val word: Word
) : Parcelable{
    fun getScore():Int {
        return time*word.difficulty/hints/(wrongs+1)
    }
}


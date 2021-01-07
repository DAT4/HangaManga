package com.example.hangamanga.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Word(
    val ID: String = "",
    var word: String = "",
    var difficulty: Int = 1,
    var description: String = "",
    var hint1: String = "",
    var hint2: String = "",
    var hint3: String = "",
    var category: String = ""
) : Parcelable {
    lateinit var letters: List<Letter>
    fun make() {
        this.letters = this.word.toList().map { Letter(it) }
    }
}
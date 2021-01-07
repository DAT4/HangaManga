package com.example.hangamanga.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Category(var title: String, val words: ArrayList<Word>) : Parcelable {

    fun getOne(): Word {
        return this.words[this.words.indices.random()]
    }
}

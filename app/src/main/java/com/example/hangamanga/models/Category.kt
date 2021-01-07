package com.example.hangamanga.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(val category: Pair<String, List<Word>>): Parcelable
package com.example.hangamanga.mvvm.Score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ScoreViewModelProviderFactory(private val scoreRepository: ScoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ScoreViewModel(scoreRepository) as T
    }

}

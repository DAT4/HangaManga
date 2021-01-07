package com.example.hangamanga.mvvm.Score

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangamanga.api.Resource
import com.example.hangamanga.models.HighScore
import com.example.hangamanga.models.Word
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.awaitResponse

class ScoreViewModel(
    private val repository: ScoreRepository
) : ViewModel() {

    val scores: MutableLiveData<Resource<List<HighScore>>> = MutableLiveData()

    init {
        getScores()
    }

    private fun getScores() = viewModelScope.launch {
        try {
            scores.postValue(Resource.Loading())
            val response = repository.getScores()
            scores.postValue(handleScoreResponse(response))
        } catch (e: Exception) {
            println(e)
        }
    }

    private fun handleScoreResponse(response: Response<List<HighScore>>) : Resource<List<HighScore>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}
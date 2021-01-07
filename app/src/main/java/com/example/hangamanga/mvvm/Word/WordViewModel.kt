package com.example.hangamanga.mvvm.Word

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangamanga.api.Resource
import com.example.hangamanga.models.Word
import kotlinx.coroutines.launch
import retrofit2.Response

class WordViewModel(
    private val repository: WordRepository
) : ViewModel() {

    val words: MutableLiveData<Resource<List<Word>>> = MutableLiveData()

    init {
        getWords()
    }

    private fun getWords() = viewModelScope.launch {
        try {
            words.postValue(Resource.Loading())
            val response = repository.getWords()
            words.postValue(handleWordResponse(response))
        } catch (e: Exception) {
            println(e)
        }
    }

    private fun handleWordResponse(response: Response<List<Word>>) : Resource<List<Word>> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}
package com.example.hangamanga.mvvm.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hangamanga.api.Resource
import com.example.hangamanga.models.HighScore
import com.example.hangamanga.models.Token
import com.example.hangamanga.models.User
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val token: MutableLiveData<Resource<Token>> = MutableLiveData()
    private lateinit var user: User

    private fun getToken(usr: User) = viewModelScope.launch {
        user = usr
        try {
            token.postValue(Resource.Loading())
            val response = repository.getToken(usr)
            token.postValue(handleUserResponse(response))
        } catch (e: Exception) {
            println(e)
        }
    }

    private fun handleUserResponse(response: Response<Token>) : Resource<Token> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}
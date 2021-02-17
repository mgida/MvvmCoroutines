package com.example.mvvmcoroutines.ui.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmcoroutines.model.UserResponse
import com.example.mvvmcoroutines.repository.UserRepository
import com.example.mvvmcoroutines.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class UserViewModel(private val userRepo: UserRepository) : ViewModel() {

    val users: MutableLiveData<Resource<UserResponse>> = MutableLiveData()

    init {
        getUsers()
    }

    private fun getUsers() = viewModelScope.launch {
        users.postValue(Resource.Loading())
        val usersResponse = userRepo.getUsers()
        users.postValue(handleUsersData(usersResponse))
    }

    private fun handleUsersData(usersResponse: Response<UserResponse>): Resource<UserResponse>? {
        if (usersResponse.isSuccessful) {
            usersResponse.body()?.let { userResponse ->
                return Resource.Success(userResponse)
            }
        }
        return Resource.Error(errorMessage = usersResponse.message())
    }
}
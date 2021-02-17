package com.example.mvvmcoroutines.repository

import com.example.mvvmcoroutines.rest.RetrofitInstance

class UserRepository {
    suspend fun getUsers() = RetrofitInstance.userApiInterface.getUsers()
}
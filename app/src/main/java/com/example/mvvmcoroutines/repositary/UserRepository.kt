package com.example.mvvmcoroutines.repositary

import com.example.mvvmcoroutines.rest.RetrofitInstance

class UserRepository {
    suspend fun getUsers() = RetrofitInstance.userApiInterface.getUsers()
}
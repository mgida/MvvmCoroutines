package com.example.mvvmcoroutines.repository

import com.example.mvvmcoroutines.database.UserDatabase
import com.example.mvvmcoroutines.model.UserResponseItem
import com.example.mvvmcoroutines.rest.RetrofitInstance

class UserRepository(private val db: UserDatabase) {

    suspend fun getUsers() = RetrofitInstance.userApiInterface.getUsers()

    suspend fun insert(userResponseItem: UserResponseItem) =
        db.userDao().insert(userResponseItem)

    fun getSavedUsers() =
        db.userDao().getAllUsers()
}
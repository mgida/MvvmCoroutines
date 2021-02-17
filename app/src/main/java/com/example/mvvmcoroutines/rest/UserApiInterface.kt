package com.example.mvvmcoroutines.rest

import com.example.mvvmcoroutines.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserApiInterface {

    @GET("users")
    suspend fun getUsers(): Response<UserResponse>

}
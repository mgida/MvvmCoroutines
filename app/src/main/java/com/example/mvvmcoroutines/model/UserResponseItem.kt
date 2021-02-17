package com.example.mvvmcoroutines.model

import com.example.mvvmcoroutines.model.Address
import com.example.mvvmcoroutines.model.Company

data class UserResponseItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)
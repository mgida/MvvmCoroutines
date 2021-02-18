package com.example.mvvmcoroutines.model

import android.os.Parcelable
import com.example.mvvmcoroutines.model.Address
import com.example.mvvmcoroutines.model.Company
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserResponseItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) : Parcelable
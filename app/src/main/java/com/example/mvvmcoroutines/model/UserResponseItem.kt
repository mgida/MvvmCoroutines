package com.example.mvvmcoroutines.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvmcoroutines.model.Address
import com.example.mvvmcoroutines.model.Company
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "users")
@Parcelize
data class UserResponseItem(
    val address: Address,
    val company: Company,
    val email: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int?=null,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) : Parcelable
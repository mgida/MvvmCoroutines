package com.example.mvvmcoroutines.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val city: String,
    val street: String,
    val suite: String,
    val zipcode: String
) : Parcelable
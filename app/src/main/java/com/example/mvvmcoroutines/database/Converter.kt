package com.example.mvvmcoroutines.database

import androidx.room.TypeConverter
import com.example.mvvmcoroutines.model.Address
import com.example.mvvmcoroutines.model.Company

class Converter {
    @TypeConverter
    fun fromAddress(address: Address): String {
        return address.city
    }

    @TypeConverter
    fun toAddress(city: String): Address {
        return Address(city, city, city, city)
    }

    @TypeConverter
    fun fromCompany(company: Company): String {
        return company.name
    }

    @TypeConverter
    fun toCompany(name: String): Company {
        return Company(name, name, name)
    }

}
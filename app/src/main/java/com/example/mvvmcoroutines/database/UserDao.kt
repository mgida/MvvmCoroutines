package com.example.mvvmcoroutines.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmcoroutines.model.UserResponseItem

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userResponseItem: UserResponseItem): Long

    @Query("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<UserResponseItem>>

}
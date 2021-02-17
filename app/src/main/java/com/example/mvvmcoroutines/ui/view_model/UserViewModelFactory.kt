package com.example.mvvmcoroutines.ui.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmcoroutines.repository.UserRepository


class UserViewModelFactory(private val userRepo: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(userRepo) as T
    }
}
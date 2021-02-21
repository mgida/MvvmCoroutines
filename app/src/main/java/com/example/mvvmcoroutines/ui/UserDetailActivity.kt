package com.example.mvvmcoroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmcoroutines.R
import com.example.mvvmcoroutines.database.UserDatabase
import com.example.mvvmcoroutines.model.UserResponseItem
import com.example.mvvmcoroutines.repository.UserRepository
import com.example.mvvmcoroutines.ui.view_model.UserViewModel
import com.example.mvvmcoroutines.ui.view_model.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    lateinit var db: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        db = UserDatabase.getInstance(applicationContext)


        val userRepository = UserRepository(db)
        val userViewModelFactory =
            UserViewModelFactory(
                userRepository
            )
        userViewModel = ViewModelProvider(this, userViewModelFactory)[UserViewModel::class.java]

        val intent = intent
        val user: UserResponseItem? = intent.getParcelableExtra("userKey")

        user?.let { user ->
            tvUserNameDetail.text = user.name
            tvUserPhoneDetail.text = user.phone
            tvUserAddressDetail.text = user.address.city
            tvUserCompanyDetail.text = user.company.name
        }

        btnSave.setOnClickListener {
            user?.let { user ->
                userViewModel.insert(user)
                Toast.makeText(applicationContext, "saved successfully", Toast.LENGTH_SHORT).show()
            }
        }


    }


}
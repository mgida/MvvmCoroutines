package com.example.mvvmcoroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmcoroutines.R
import com.example.mvvmcoroutines.model.UserResponseItem
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val intent = intent
        val user: UserResponseItem? = intent.getParcelableExtra("userKey")

        user?.let { user ->
            tvUserNameDetail.text = user.name
            tvUserPhoneDetail.text = user.phone
            tvUserAddressDetail.text = user.address.city
            tvUserCompanyDetail.text = user.company.name
        }


    }
}
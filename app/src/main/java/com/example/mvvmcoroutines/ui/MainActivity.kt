package com.example.mvvmcoroutines.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmcoroutines.R
import com.example.mvvmcoroutines.adapters.UserAdapter
import com.example.mvvmcoroutines.repositary.UserRepository
import com.example.mvvmcoroutines.utils.Resource
import com.example.mvvmcoroutines.view_model.UserViewModel
import com.example.mvvmcoroutines.view_model.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userAdapter = UserAdapter()
        setUpRecyclerView()


        val userRepositary = UserRepository()
        val userViewModelFactory = UserViewModelFactory(userRepositary)
        userViewModel = ViewModelProvider(this, userViewModelFactory)[UserViewModel::class.java]


        userViewModel.users.observe(this@MainActivity, Observer { response ->
            when (response) {
                is Resource.Loading -> showProgressbar()
                is Resource.Success -> {
                    hideProgressbar()
                    response.data?.let { userResponse ->
                        userAdapter.differ.submitList(userResponse)
                    }
                }
                else -> {
                    hideProgressbar()
                    response.errorMessage?.let { it ->
                        Log.d(TAG, "something went wrong: ${it.toString()}")
                    }
                }

            }
        })

    }


    private fun hideProgressbar() {
        pb_indicator.visibility = View.INVISIBLE
    }

    private fun showProgressbar() {
        pb_indicator.visibility = View.VISIBLE
    }

    private fun setUpRecyclerView() {
        rvUsers.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

}
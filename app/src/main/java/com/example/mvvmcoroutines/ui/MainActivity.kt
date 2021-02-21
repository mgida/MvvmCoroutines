package com.example.mvvmcoroutines.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmcoroutines.R
import com.example.mvvmcoroutines.adapters.UserAdapter
import com.example.mvvmcoroutines.database.UserDatabase
import com.example.mvvmcoroutines.model.UserResponseItem
import com.example.mvvmcoroutines.repository.UserRepository
import com.example.mvvmcoroutines.utils.Resource
import com.example.mvvmcoroutines.ui.view_model.UserViewModel
import com.example.mvvmcoroutines.ui.view_model.UserViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    lateinit var userAdapter: UserAdapter
    lateinit var db: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = UserDatabase.getInstance(applicationContext)
        userAdapter = UserAdapter()
        setUpRecyclerView()

        val userRepository = UserRepository(db)
        val userViewModelFactory =
            UserViewModelFactory(
                userRepository
            )
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

        userAdapter.onItemClick = { user ->
            startDetailActivityOf(user)
        }

    }

    private fun startDetailActivityOf(user: UserResponseItem) {
        val intent = Intent(this@MainActivity, UserDetailActivity::class.java)
        intent.putExtra("userKey", user)
        startActivity(intent)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.user_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_user -> {
                userViewModel.getSavedUsers().observe(this, Observer {
                    Toast.makeText(applicationContext, "${it.count()}", Toast.LENGTH_LONG).show()
                })
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}
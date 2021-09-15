package com.demo.assignment_priyanka.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.assignment_priyanka.Adapter.UserAdapter
import com.demo.assignment_priyanka.Database.UserDb
import com.demo.assignment_priyanka.Database.UserModelRoomDb
import com.demo.assignment_priyanka.MainActivity
import com.demo.assignment_priyanka.R
import com.demo.assignment_priyanka.RoomRepository.UserRoomRepository
import com.demo.assignment_priyanka.Utils.CoroutineMethods
import com.demo.assignment_priyanka.ViewModels.UserViewModel
import com.demo.assignment_priyanka.ViewModels.UserViewModelFactory
import com.demo.assignment_priyanka.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(),View.OnClickListener {
    val binding: ActivityUserBinding by lazy{
        ActivityUserBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel : UserViewModel
    private lateinit var repository : UserRoomRepository
    private lateinit var factory : UserViewModelFactory
    private lateinit var database : UserDb

    private lateinit var userAdapter: UserAdapter
    private var modelList : ArrayList<UserModelRoomDb> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = UserDb(this)
        repository = UserRoomRepository(database)
        factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory)[UserViewModel::class.java]
        getUserList()
        binding.back.setOnClickListener(this)
    }
    private fun getUserList() {
        CoroutineMethods.main {
            viewModel.getAllUser().observe(this, {
                if (it == null) {
                    Toast.makeText(this, "Sorry no users found", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    modelList = it as ArrayList<UserModelRoomDb>
                    userAdapter = UserAdapter(this,modelList)
                    binding.recRecentusers.adapter = userAdapter
                    binding.recRecentusers.itemAnimator = DefaultItemAnimator()
                    binding.recRecentusers.layoutManager = GridLayoutManager(this, 1)
                }
            })
        }
    }
    override fun onClick(v: View?) {
       when(v?.id){
          R.id.back->{
              val i= Intent(this, MainActivity::class.java)
              startActivity(i)
          }
        }
    }
}
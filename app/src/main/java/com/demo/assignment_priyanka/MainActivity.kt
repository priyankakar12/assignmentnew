package com.demo.assignment_priyanka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.demo.assignment_priyanka.Activity.AddUserActivity
import com.demo.assignment_priyanka.Activity.UserActivity
import com.demo.assignment_priyanka.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnAddUser.setOnClickListener(this)
        binding.userList.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
       when(v?.id){
           R.id.btn_add_user ->{
            val i= Intent(this, AddUserActivity::class.java)
               startActivity(i)
           }
           R.id.user_list ->{
               val i= Intent(this, UserActivity::class.java)
               startActivity(i)
           }

       }
    }
}
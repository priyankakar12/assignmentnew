package com.demo.assignment_priyanka.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.demo.assignment_priyanka.Database.UserDb
import com.demo.assignment_priyanka.Database.UserModelRoomDb
import com.demo.assignment_priyanka.MainActivity
import com.demo.assignment_priyanka.R
import com.demo.assignment_priyanka.RoomRepository.UserRoomRepository
import com.demo.assignment_priyanka.Utils.CoroutineMethods
import com.demo.assignment_priyanka.ViewModels.UserViewModel
import com.demo.assignment_priyanka.ViewModels.UserViewModelFactory
import com.demo.assignment_priyanka.databinding.ActivityAddUserBinding
import java.io.InputStream


class AddUserActivity : AppCompatActivity(),View.OnClickListener {
    val binding: ActivityAddUserBinding by lazy{
        ActivityAddUserBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel : UserViewModel
    private lateinit var repository : UserRoomRepository
    private lateinit var factory : UserViewModelFactory
    private lateinit var database : UserDb
    private var user : UserModelRoomDb? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        database = UserDb(this)
        repository = UserRoomRepository(database)
        factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this,factory)[UserViewModel::class.java]

        binding.btnAddUser.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
      when(v?.id){
          R.id.btn_add_user->{
              val id = if (user != null) user?.id else null
              var mName: String = binding.edName.text.toString()
              var mPhone: String = binding.edPhonenumber.text.toString()
              var mEmail: String = binding.edEmailaddress.text.toString()
              var mAddress: String = binding.edAddress.text.toString()
              var cancel = false
              var focusView: View? = null
              if(TextUtils.isEmpty(mName))
              {
                  Toast.makeText(this, "User name cannot be blank", Toast.LENGTH_SHORT).show()
                  focusView = binding.edName
                  cancel = true
              }
              else if (TextUtils.isEmpty(mEmail))
              {
                  Toast.makeText(this, "User email cannot be blank", Toast.LENGTH_SHORT).show()
                  focusView = binding.edEmailaddress
                  cancel = true
              }
              else if (!isEmailValid(mEmail)){
                  Toast.makeText(this, "Please insert valid email", Toast.LENGTH_SHORT).show()
                  focusView = binding.edEmailaddress
                  cancel = true
              }
              else if (TextUtils.isEmpty(mPhone))
              {
                  Toast.makeText(this, "User phone cannot be blank", Toast.LENGTH_SHORT).show()
                  focusView = binding.edPhonenumber
                  cancel = true
              }
              else if (TextUtils.isEmpty(mAddress))
              {
                  Toast.makeText(this, "User address cannot be blank", Toast.LENGTH_SHORT).show()
                  focusView = binding.edAddress
                  cancel = true
              }
              else if (!isPhoneValid(mPhone))
              {
                  Toast.makeText(this, "Please insert valid phone number", Toast.LENGTH_SHORT).show()
                  focusView = binding.edPhonenumber
                  cancel = true
              }

              if (cancel) {
                  focusView?.requestFocus()
              }
              else{

                  val user = UserModelRoomDb(id,mName,mEmail,mPhone,mAddress)
                  CoroutineMethods.main {
                      viewModel.insertUser(user).also {
                          Toast.makeText(this,"Successfully save user", Toast.LENGTH_SHORT).show()
                          val i= Intent(this, MainActivity::class.java)
                          startActivity(i)
                      }
                  }

              }
          }

      }
    }
    private fun isPhoneValid(phone: String): Boolean {
        return phone.length > 9
    }
    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
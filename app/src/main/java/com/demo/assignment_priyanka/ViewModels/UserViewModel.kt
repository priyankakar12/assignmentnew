package com.demo.assignment_priyanka.ViewModels

import androidx.lifecycle.ViewModel
import com.demo.assignment_priyanka.Database.UserModelRoomDb
import com.demo.assignment_priyanka.RoomRepository.UserRoomRepository


class UserViewModel(private val userRoomRepository: UserRoomRepository) : ViewModel() {

    suspend fun insertUser (user : UserModelRoomDb) = userRoomRepository.insertUser(user)

    fun getAllUser() = userRoomRepository.getAllUserList()



}
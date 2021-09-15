package com.demo.assignment_priyanka.RoomRepository

import androidx.lifecycle.LiveData
import com.demo.assignment_priyanka.Database.UserDb
import com.demo.assignment_priyanka.Database.UserModelRoomDb


class UserRoomRepository(private val userdb: UserDb) {
    suspend fun insertUser(user : UserModelRoomDb) = userdb.getUserDao().insertUser(user)

    fun getAllUserList () : LiveData<List<UserModelRoomDb>> = userdb.getUserDao().getAllUser()



}
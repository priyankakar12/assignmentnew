package com.demo.assignment_priyanka.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.assignment_priyanka.RoomRepository.UserRoomRepository


class UserViewModelFactory(private val repository: UserRoomRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        try {
            val constructor = modelClass.getDeclaredConstructor(UserRoomRepository::class.java)
            return constructor.newInstance(repository)
        } catch (e: Exception) {
            Log.d("dtgfh",e.message.toString())
        }
        return super.create(modelClass)
    }
}
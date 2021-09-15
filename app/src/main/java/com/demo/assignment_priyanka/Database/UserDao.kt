package com.demo.assignment_priyanka.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userModel: UserModelRoomDb)

    @Query("SELECT * FROM user_db ORDER BY name ASC")
    fun getAllUser(): LiveData<List<UserModelRoomDb>>


}